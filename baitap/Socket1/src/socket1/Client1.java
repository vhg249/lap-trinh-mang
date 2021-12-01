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

/**
 *
 * @author a
 */
public class Client1 {
    Socket mySocket = null;
    DataOutputStream os = null;
    DataInputStream is = null;
    
    public void connection(){
        try {
            InetAddress inet = InetAddress.getByName("14.177.7.124");
            System.out.println(inet.getHostName());
            mySocket = new Socket(inet, 11100);
            os = new DataOutputStream(mySocket.getOutputStream());
            is = new DataInputStream(mySocket.getInputStream());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void send(String s){
        try {
            os.writeUTF(s);
            System.out.println("sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String receive(){
        System.out.println(is);
        if(mySocket != null & is != null){
            try {
                String res;
                System.out.println(is.readUTF());
                if((res = is.readUTF()) != null){
                    System.out.println("received");
                    return res;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "error";
    }
}
