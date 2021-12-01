/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclient;

import control.IRegistration;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import model.Student;
/**
 *
 * @author a
 */
public class RMIClient {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        // TODO code application logic here
        IRegistration i = null;
        Student student = new Student("B18DCAT119", "Nguyen Viet Huong", "14.226.25.185", 4);
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            String arr[] = reg.list();
            for(int j = 0; j< arr.length; j++){
                System.out.println(arr[j]);
            }
            i = (IRegistration) reg.lookup("Server");
            i.register(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
