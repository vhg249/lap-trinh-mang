package com.Server;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerWorld implements Runnable {

    private Socket sckt = null;
    private Connection conn = null;
    private final int playerIDwhole;
    private static int numPlayer = 0;
    private static int cp2 =0, cp3= 0, cp4 = 0, qp2 =0, qp3= 0, qp4 = 0;
    private int mwID = 0;
    private boolean breakfromwhile = false;

    private BufferedReader inFromClient = null;
    private PrintWriter outToClient = null;
    private FileWriter writefile = null;
    private BufferedWriter bw = null;

    private static final int[] playerIDwholelistc2 = new int[2];
    private static final int[] playerIDwholelistc3 = new int[3];
    private static final int[] playerIDwholelistc4 = new int[4];

    private static final int[] playerIDwholelistq2 = new int[2];
    private static final int[] playerIDwholelistq3 = new int[3];
    private static final int[] playerIDwholelistq4 = new int[4];


    public static int getCp2() {
        return cp2;
    }

    public static int getCp3() {
        return cp3;
    }

    public static int getCp4() {
        return cp4;
    }

    public static int getQp2() {
        return qp2;
    }

    public static int getQp3() {
        return qp3;
    }

    public static int getQp4() {
        return qp4;
    }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public PrintWriter getOutToClient() {
        return outToClient;
    }

    public PlayerWorld(Socket connectionSocket, int playerIDwhole, int ctwoPlayers, int cthreePlayers, int cfourPlayers, int qtwoPlayers, int qthreePlayers, int qfourPlayers) throws IOException, SQLException, ClassNotFoundException {

        this.sckt = connectionSocket;
        this.playerIDwhole = playerIDwhole;

        try
        {
            inFromClient = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
            outToClient = new PrintWriter(sckt.getOutputStream());

        }
        catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        cp2 = ctwoPlayers;
        cp3 = cthreePlayers;
        cp4 = cfourPlayers;

        qp2 = qtwoPlayers;
        qp3 = qthreePlayers;
        qp4 = qfourPlayers;
        ///constructor ends

        writefile = new FileWriter(GameServer.getFile(), true);
        bw = new BufferedWriter(writefile);
    }

    public void run()
    {
        MonopolyWorld mw;
        String str = "";
        String mode = "";


        while (true)
        {

            try {
                System.out.println(" player "+playerIDwhole+" ---"+str);
                str = inFromClient.readLine();
                System.out.println("   player "+playerIDwhole+" username password: "+str);

            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] newmsg =  str.split("###@###");

            String username =newmsg[0];
            String password =newmsg[1];

            try {
                bw.write(" "+username+" "+password);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Register success");

            try {
                 mode = inFromClient.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(" player "+playerIDwhole+"---"+str);
                str = inFromClient.readLine();
                System.out.println("   player "+playerIDwhole+" str: "+str);

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException npe) {
                System.out.println("nulllptr");
            }
            
            if(str.equals("2") || str.equals("3") || str.equals("4"))
            {
                numPlayer = Integer.parseInt(str);

                System.out.println(numPlayer+" players to start match");


                if(numPlayer == 2 && mode.equals("classic") && cp2<2)
                {
                    cp2 ++;
                    playerIDwholelistc2[cp2-1] = playerIDwhole;

                }

                if(numPlayer == 3 && mode.equals("classic") && cp3<3)
                {
                    cp3 ++;
                    playerIDwholelistc3[cp3-1] = playerIDwhole;

                }

                if(numPlayer == 4 && mode.equals("classic") && cp4<4)
                {
                    cp4 ++;
                    playerIDwholelistc4[cp4-1] = playerIDwhole;

                }

                if(numPlayer == 2 && mode.equals("quick match") && qp2<2)
                {
                    qp2 ++;
                    playerIDwholelistq2[qp2-1] = playerIDwhole;

                }

                if(numPlayer == 3 && mode.equals("quick match") && qp3<3)
                {
                    qp3 ++;
                    playerIDwholelistq3[qp3-1] = playerIDwhole;

                }

                if(numPlayer == 4 && mode.equals("quick match") && qp4<4)
                {
                    qp4 ++;
                    playerIDwholelistq4[qp4-1] = playerIDwhole;

                }


                if(numPlayer == 2 && mode.equals("classic") && cp2 == 2)
            {
                mwID++;

                mw = new MonopolyWorld(2, mwID, playerIDwholelistc2, mode);
                GameServer.mwList.add(mw);

                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.println("loading finished");
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.flush();
                }

                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    try {
                        String lmsg = GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).inFromClient.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Thread t = new Thread(mw);
                t.start();

                cp2 = 0;
                System.out.println("2 player game started");

                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.flush();
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).breakfromwhile = true;
                }
            }
                if(numPlayer == 3&& mode.equals("classic")  && cp3 == 3)
                {
                    mwID++;

                    mw = new MonopolyWorld(3, mwID, playerIDwholelistc3, mode);
                    GameServer.mwList.add(mw);


                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.println("loading finished");
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.flush();
                    }

                    
                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Thread t = new Thread(mw);
                    t.start();

                    cp3 = 0;
                    System.out.println("3 player game started");

                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).breakfromwhile = true;
                    }
                }


                if(numPlayer == 4 && mode.equals("classic")&& cp4 == 4)
                {
                    mwID++;

                    mw = new MonopolyWorld(4, mwID, playerIDwholelistc4, mode);
                    GameServer.mwList.add(mw);


                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.println("loading finished");
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.flush();
                    }

                    
                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    Thread t = new Thread(mw);
                    t.start();

                    cp4 = 0;
                    System.out.println("4 player game started");

                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 2 && mode.equals("quick match") && qp2 == 2)
                {
                    mwID++;

                    mw = new MonopolyWorld(2, mwID, playerIDwholelistq2, mode);
                    GameServer.mwList.add(mw);

                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.println("loading finished");
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.flush();
                    }

                    
                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    Thread t = new Thread(mw);
                    t.start();

                    qp2 = 0;
                    System.out.println("2 player game started");

                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 3 && mode.equals("quick match") && qp3 == 3)
                {
                    mwID++;

                    mw = new MonopolyWorld(3, mwID, playerIDwholelistq3, mode);
                    GameServer.mwList.add(mw);


                    ///loading finished howar request
                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.println("loading finished");
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.flush();
                    }

                    
                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Thread t = new Thread(mw);
                    t.start();

                    qp3 = 0;
                    System.out.println("3 player game started");

                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 4 &&  mode.equals("quick match") && qp4 == 4)
                {
                    mwID++;

                    mw = new MonopolyWorld(4, mwID, playerIDwholelistq4, mode);
                    GameServer.mwList.add(mw);


                    ///loading finished howar request
                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.println("loading finished");
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.flush();
                    }

                    
                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Thread t = new Thread(mw);
                    t.start();

                    qp4 = 0;
                    System.out.println("4 player game started");

                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.println("enableStartPlaying"+ idx);
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).breakfromwhile = true;
                    }
                }

            }

            while (!breakfromwhile)
            {
            }
            if(breakfromwhile)
            {
                System.out.println("i am player "+playerIDwhole+ " breaking from while");
                break;
            }
        }

    }
    private boolean checkLogin(String username, String password){
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
