
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nguye
 */
public class UDPServer {

    public static void main(String[] args) throws SocketException, IOException {
        System.out.println("Server is running...");
        int port = 5000;
        DatagramSocket myServer = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        myServer.receive(receivePacket);
        String input = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(input + " " + port +" " +receivePacket.getAddress());

        InetAddress IPAddress = receivePacket.getAddress();
        int port2 = receivePacket.getPort();
        byte[] sendData = new byte[1024];
        String str = "Server da nhan duoc du lieu, ket thuc";
        sendData = str.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port2);
        myServer.send(sendPacket);
    }

}
