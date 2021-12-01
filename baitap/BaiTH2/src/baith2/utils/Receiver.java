/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baith2.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 *
 * @author a
 */
public class Receiver {
    private final Socket socket;
    private DataInputStream dis;
    private ObjectInputStream ois;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object receiveObject() {
        if (socket != null && ois != null) {
            try {
                Object object = ois.readObject();
                if (object != null){
                    return object;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String receiveString() {
        System.out.println("adsads");
        if (socket != null && dis != null) {
            try {
                String s = dis.readUTF();
                System.out.println(s);
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int reveiceInt(){
        int a = -1;
        try {
            if(socket != null & dis != null){
                a = dis.readInt();
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
