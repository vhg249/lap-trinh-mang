/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojdbc;

import demojdbc.controller.EmployeeControl;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import demojdbc.view.EmployeeView;
/**
 *
 * @author a
 */
public class DemoJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
//            System.out.println("Get connection ... ");
//            Connection conn = DataConnection.getConnection();
//            System.out.println("Get connection " + conn);
//            System.out.println("Done!");
            
            EmployeeView view = new EmployeeView();
            view.setVisible(true);
            EmployeeControl empControl = new EmployeeControl(view);
            empControl.run();
        } catch (Throwable ex) {
            Logger.getLogger(DemoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
