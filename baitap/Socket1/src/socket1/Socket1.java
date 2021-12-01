/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author a
 */
public class Socket1 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Client1 c = new Client1();
        c.connection();
//        System.out.println("Nhap xau: ");
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        System.out.println("Done");
//        c.send(s);
//        c.receive();
//        String res = c.receive();
//        System.out.println(res);
    }
    
}
