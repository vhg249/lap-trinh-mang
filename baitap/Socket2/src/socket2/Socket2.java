/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket2;

import model.Answer;
import model.Student;

/**
 *
 * @author a
 */
public class Socket2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client c = new Client();
        c.connection();
        
        String res = c.receiveString();
        System.out.println(res);
        Student student = new Student("B18DCAT119", "Nguyễn Việt Hương", String.valueOf(c.mySocket.getLocalAddress()), 4);
        c.sendObject(student);
        System.out.println( c.receiveString());
        c.sendString("B18DCAT119");
        System.out.println(c.receiveString());
//        c.sendString(student.getMasv());
//        System.out.println(c.receiveString());
//        c.sendInt(student.getNhom());
//        System.out.println(c.reveiceInt());
//        Answer answer = (Answer) c.receiveObject();
//        System.out.println(answer.getS().getMasv());
//        c.sendString("Answer");
    }
    
}
