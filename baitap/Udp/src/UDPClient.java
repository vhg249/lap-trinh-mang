
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class UDPClient {
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException{
            DatagramSocket client = new DatagramSocket();
            System.out.println("Client is running...");
            System.out.print("Nhap vao tin nhan: ");
            Scanner sc  = new Scanner(System.in);
            String send = sc.nextLine();
            byte[] sendByte = send.getBytes();
            InetAddress remoteAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length, remoteAddress, 5000);     
            client.send(sendPacket);
            
            byte[] receive = new byte[4096];
            DatagramPacket incoming = new DatagramPacket(receive, receive.length);
            client.receive(incoming);
            System.out.println(new String(incoming.getData(), 0, incoming.getLength()));    
            
            client.close();
    }
}
