/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baith2;

import baith2.model.Answer;
import baith2.model.Student;
import baith2.utils.Receiver;
import baith2.utils.Sender;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author a
 */
public class Client {
    Socket mySocket = null;

    public void connection() {
        try {
            mySocket = new Socket("14.226.25.185", 11300);
            System.out.println("connected");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connection();
        Receiver receiver = new Receiver(client.mySocket);
        Sender sender = new Sender(client.mySocket);
        String res = receiver.receiveString();
        System.out.println(res);
//        Student student = new Student("B18DCAT119", "Nguyễn Việt Hương", String.valueOf(client.mySocket.getLocalAddress()), 4);
//        sender.sendObject(student);
//        System.out.println(receiver.reveiceInt());
//        sender.sendString(student.getHovaten());
//        System.out.println(receiver.receiveString());
//        sender.sendString(student.getMaSV());
//        System.out.println(receiver.receiveString());
//        sender.sendInt(student.getGroup());
//        System.out.println(receiver.reveiceInt());
//        Answer answer = (Answer) receiver.receiveObject();
//        System.out.println(answer.getStudent().getMaSV());
//        sender.sendString("Answer");
    }
}
