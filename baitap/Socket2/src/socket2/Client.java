/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author a
 */
public class Client {
    Socket mySocket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    DataOutputStream os = null;
    DataInputStream is = null;
    
    public void connection(){
        try {
            InetAddress inet = InetAddress.getByName("14.226.25.185");
            System.out.println(inet.getHostName());
            mySocket = new Socket(inet, 11300);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
            os = new DataOutputStream(mySocket.getOutputStream());
            is = new DataInputStream(mySocket.getInputStream());
            System.out.println("connected");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void sendObject(Student st){
        try {
            oos.writeObject(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendString(String str) {
        try {
            os.writeUTF(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void sendInt(int a){
        try {
            os.writeInt(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String receiveString(){
        try {
            if(mySocket != null & is != null){
                String str = is.readUTF();
                return str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int reveiceInt(){
        int a = -1;
        try {
            if(mySocket != null & is != null){
                a = is.readInt();
                return a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
    public Object receiveObject(){
        if (mySocket != null && ois != null) {
            try {
                Object o = ois.readObject();
                if (o != null){
                    return o;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
