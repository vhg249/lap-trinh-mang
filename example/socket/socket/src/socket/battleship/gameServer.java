package socket.battleship;

import java.io.*;
import java.net.*;
import java.util.Arrays;

class gameServer 
{
	public static void main(String args[]) throws Exception
	{
		int port = 0;
		if(args.length < 1)
		{
			System.err.println("Error: Please include a port number");
			//System.exit(1);
		}
		try
		{
			port = 9999;//Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e)
		{
			System.err.println("Error: Argument could not be parsed into an integer");
			System.exit(1);
		}
		ServerGame Server = new ServerGame(port);
		Server.start();
	}
}

class ServerGame
{
	ServerNetWorkIO IO;
	private InetAddress[] IPAddress;
	private String[] names;
	private int ports[];
	private JShipGame game;
	boolean shipIDs[];
	boolean activeGame;
	public ServerGame(int port)
	{
		IO = new ServerNetWorkIO(port);
		IPAddress = new InetAddress[2];
		names = new String[2];
		ports = new int[2];
		shipIDs = new boolean[]{false, false};
		activeGame = false;
	}

	void start()
	{
		while(true)
		{
			System.out.println("Waiting for packet...");
			DatagramPacket packet = IO.getPacket();
			if(packet != null)
			{
				ProccessInput(packet);
			}
		}
	}

	public void ProccessInput(DatagramPacket packet)
	{
		String packetMessage = new String(packet.getData());
		String name = packetMessage.substring(0, packetMessage.indexOf(':'));
		String message = packetMessage.substring(packetMessage.indexOf(':')+1, packetMessage.indexOf(0));
		int PlayerIndex = GetIndex(packet.getAddress(), name);
		String result="";
		if(PlayerIndex == -1)
		{
			if(message.equals("join"))
			{ 
				PlayerIndex = CreatePlayerindex(packet.getAddress(), name, packet.getPort());
				if(PlayerIndex == -1)
				{
					result = "bad:Server full";
				}
				else
				{
					result = "good:Welcome to the server, "+name+"!";
				}
				if(!(IPAddress[0] == null) && !(IPAddress[1] == null))
				{
					activeGame = true;
					game = new JShipGame();
				}
			}
			
		}
		else
		{
			if(message.equals("quit") && PlayerIndex != -1)
			{
				RemovePlayerindex(PlayerIndex);
				activeGame = false;
				shipIDs = new boolean[]{false, false};
				IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, packet.getAddress(), packet.getPort()));
				if(IPAddress[(PlayerIndex+1)%2] == null)
				{
					result = "reset:Server reset, player left";
					IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, IPAddress[(PlayerIndex+1)%2], ports[(PlayerIndex+1)%2]));
				}
				return;
			}
			else
			{
				if(!activeGame)
				{
					result = "bad:Waiting for additonal players";
				}
				else
				{
					result = processMove(PlayerIndex, name, message);
				}
				if(result.substring(0, result.indexOf(":")).equals("win"))
				{
					String out = "lose:You Lose!!";
					IO.sendPacket(new DatagramPacket(out.getBytes(), out.getBytes().length, IPAddress[(PlayerIndex+1)%2], ports[(PlayerIndex+1)%2]));
					IPAddress = new InetAddress[2];
					names = new String[2];
					ports = new int[2];
					shipIDs = new boolean[]{false, false};
					activeGame = false;
				}
			}
		}
		IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, packet.getAddress(), packet.getPort()));
	}

	private int CreatePlayerindex(InetAddress IP, String name, int port)
	{
		for(int i = 0; i < IPAddress.length; i++)
		{
			if(IPAddress[i] == null)
			{
				IPAddress[i] = IP;
				names[i] = name;
				ports[i] = port;
				System.out.println("Player: "+name+" joined from: "+IP);
				return i;
			}
		}
		return -1;
	}

	private void RemovePlayerindex(int Index)
	{
		if(Index != -1)
		{
			IPAddress[Index] = null;
			names[Index] = null;
			ports[Index] = 0;
		}
	}

	private int GetIndex(InetAddress IP, String name)
	{
		for(int i = 0; i < IPAddress.length; i++)
		{
			if(IPAddress[i] != null && IPAddress[i].equals(IP) && name.equals(names[i]))
				return i;
		}
		return -1;
	}

	private String processMove(int PlayerIndex, String name, String move)
	{
		if(shipIDs[PlayerIndex] == false)
		{
			game.assignShips(PlayerIndex, move);
			shipIDs[PlayerIndex] = true;
			return "good:Ships succesfully placed!";
		}
		else
		{
			if(!shipIDs[(PlayerIndex+1)%2])
			{
				return "bad:Waiting for other player to place ships";
			}
			int x = move.charAt(0)-97;
			int y = move.charAt(1)-48;
			int result = game.MakeMove(PlayerIndex, x, y);
			String out;
			switch(result)
			{
				case -2:
					out = "bad:It's not your turn right now!!"; break;
				case -1:
					out = "bad:You already attacked this spot!"; break;
				case 0:
					out = "good:MISS!!"; break;
				case 1:
					out = "good:HIT!!"; break;
				case 2:
					out = "win:You Win!!"; break;
				default:
					out = "error:Internal Error while makeing move"; break;
			}
			return out+'\n'+ProduceBoard(PlayerIndex);
		}
	}

	public String ProduceBoard(int PlayerIndex)
	{
		char[][][] boards = game.getPlayerView(PlayerIndex);
		String result = "Your board: \n";
		result += " abcdefghij\n";
		for(int i = 0; i < 10; i++)
		{
			result += i;
			for(int j= 0; j < 10; j++)
			{
				result += boards[0][j][i];
			}
			result += '\n';
		}
		result += "Enemy board: \nabcdefghij\n";
		for(int i = 0; i < 10; i++)
		{
			result += i;
			for(int j= 0; j < 10; j++)
			{
				result += boards[1][j][i];
			}
			result += '\n';
		}
		return result;
	}
}

class JShipGame
{
	char[][][] PlayerBoards;
	final static char empty = '-', miss = 'O', hit = 'X', occupied = 'S';
	int ActivePlayer;

	private class coordinate
	{
		public int X;
		public int Y;
		coordinate(int x, int y)
		{
			X = x;
			Y = y;
		}
	}

	JShipGame()
	{
		PlayerBoards = new char[2][10][10];
		for(int i=0; i < 10; i++)
		{
			Arrays.fill(PlayerBoards[0][i], empty);
			Arrays.fill(PlayerBoards[1][i], empty);
		}
		ActivePlayer = 0;
	}

	void assignShips(int PlayerIndex, String ships)
	{
		for(int i = 0; i < ships.length(); i += 2)
		{
			String loc = ships.substring(i, i+2);
			PlayerBoards[PlayerIndex][loc.charAt(0)-97][loc.charAt(1)-48] = 'S';
		}
	}

	int MakeMove(int PlayerIndex, int x, int y)
	{
		if(PlayerIndex != ActivePlayer)
		{
			return -2;
		}
		coordinate move = new coordinate(x, y);
		int result = Attack(PlayerIndex, move);
		switch(result)
		{
			case -1:
				return -1;
			case 0:
				ActivePlayer = (PlayerIndex+1)%2;
				return 0;
			case 1:
				if(checkWinner(ActivePlayer))
				{
					return 2;
				}
				ActivePlayer = (PlayerIndex+1)%2;
				return 1;
			default:
				return -3;
		}
	}

	private int Attack(int PlayerIndex, coordinate c)
	{
		char spot = PlayerBoards[(PlayerIndex+1)%2][c.X][c.Y];
		switch(spot)
		{
			case empty:
				PlayerBoards[(PlayerIndex+1)%2][c.X][c.Y] = miss;				
				return 0;
			case occupied:
				PlayerBoards[(PlayerIndex+1)%2][c.X][c.Y] = hit;
				return 1;
			default:
				return -1;
		}
	}

	private boolean checkWinner(int PlayerIndex)
	{
		boolean won = true;
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(PlayerBoards[(PlayerIndex+1)%2][i][j] == occupied)
				{
					won = false;
					break;
				}
			}
		}
		return won;
	}

	private char[][] ConvertToEnemyView(char[][] board)
	{
		char[][] internal = new char[10][10];
		for(int i = 0; i < 10; i++)
		{
			internal[i] = (char[])board[i].clone();
		}
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(board[j][i] == occupied)
				{
					internal[j][i] = empty;
				}
			}
		}
		return internal;
	}

	public char[][][] getPlayerView(int PlayerIndex)
	{
		char[][][] results = new char[2][10][10];
		results[0] = PlayerBoards[PlayerIndex];
		results[1] = ConvertToEnemyView(PlayerBoards[(PlayerIndex+1)%2]);
		return results;
	}
}

class ServerNetWorkIO
{
	private DatagramSocket serverSocket;
	private int port;
	private DatagramPacket Packet;
	private byte[] Data;

	ServerNetWorkIO(int port)
	{
		this.port = port;
		try
		{
			serverSocket = new DatagramSocket(port);
		}
		catch(SocketException e)
		{
			System.err.println("Error: Socket could not be created");
			System.exit(1);
		}
	}

	public DatagramPacket getPacket()
	{
		Data = new byte[1024];
		Packet = new DatagramPacket(Data, Data.length);
		try
		{
			serverSocket.receive(Packet);
		}
		catch(IOException e)
		{
			System.err.println("Error: error while recieving packet");
			return null;
		}
		return Packet;
	}

	public void sendPacket(DatagramPacket packet)
	{
		try
		{
			serverSocket.send(packet);
		}
		catch(IOException e)
		{
			System.err.println("Error: error while sending packet");
		}
	}
}
