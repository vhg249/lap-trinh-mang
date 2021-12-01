package socket.reserveStr;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class UDPServer {
	DatagramSocket myServer = null;
	String input;
	int port = 9910;

	// Mo mot server socket
	public void openServer() {
		try {
			myServer = new DatagramSocket(port);
		} catch (SocketException e) {
			System.out.println(e);
		}
	}

	public void listening() {
		byte[] receiveData = new byte[1024];
		int sendLengthData;
		while (true) {
			try {
				// Nhan du lieu
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				myServer.receive(receivePacket);
				
				
				input = new String(receivePacket.getData());
				
				System.out.println("Receive data "+input +" from " + receivePacket.getAddress().getHostName());
				
				// Xu li du lieu
				ReverseString str = new ReverseString(input);
				str.reverse();
		
				// Dong goi thong tin du lieu can tra lai
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				ObjectOutput out = new ObjectOutputStream(baos);
//				out.writeObject(str);
//				//out.flush();
//				byte[] oo = baos.toByteArray();
//				
//				sendLengthData = oo.length;
//				
//				DatagramPacket sendPacket = new 
//						DatagramPacket(oo, oo.length, IPAddress, port);
				
				String tmpString = str.get_string();
				DatagramPacket sendPacket = new 
						DatagramPacket(tmpString.getBytes(), tmpString.length(), 
								IPAddress, port);
				// Gui du lieu ve client
				myServer.send(sendPacket);
			} catch (SocketException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	public static void main(String[] args) {
		UDPServer server = new UDPServer();
			server.openServer();
			server.listening();
	}
}