/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Answer;
import model.Student;

/**
 *
 * @author a
 */
public class TCPClient {

    Socket mySocket = null;
    DataOutputStream  dos = null;
    DataInputStream dis = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        client.connect();
        String[] response = client.receiveString().split(";");
       
        client.run(response);
        client.close();
    }
    
    private void connect() {
        try {
            this.mySocket = new Socket("14.226.25.185",11300);
            this.oos = new ObjectOutputStream(mySocket.getOutputStream());
            this.dos = new DataOutputStream(mySocket.getOutputStream());
            this.ois = new ObjectInputStream(mySocket.getInputStream());
            this.dis = new DataInputStream(mySocket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void run(String[] response){
        Student student = new Student("B18DCAT119", "Nguyen Viet Huong", "14.226.25.185", 4);
        sendObject(student);
        System.out.println(receiveString());
        sendString("B18DCAT119");
        System.out.println(receiveString());
        sendString("Nguyen Viet Huong");
        System.out.println(receiveString());
        sendInt(4);
        System.out.println(receiveInt());
        Answer answer = receiveObject();
        System.out.println(answer);
        sendString("Answer");
    }
    
    private void sendInt(int i) {
        try {
            dos.writeInt(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sendString(String str) {
        try {
            dos.writeUTF(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendObject(Student student) {
        try {
            oos.writeObject(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int receiveInt() {
        if (mySocket != null && dis != null) {
            try {
                int val;
                val = dis.readInt();
                if(val != 0) return val;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    private String receiveString() {
        if(mySocket != null && dis != null){
            try {
                String res;
                if ((res = dis.readUTF()) != null) {
                    return res;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    

    private Answer receiveObject() {
         if(mySocket != null && ois != null){
            try {
                Object o;
                if ((o = ois.readObject()) != null) {
                    if(o instanceof Answer){
                        Answer res  = (Answer)o;
                        return res;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void close() {
        if(mySocket != null && dis != null && dos != null && oos != null && ois != null){
            try {
                dis.close();
                dos.close();
                oos.close();
                ois.close();
                mySocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
