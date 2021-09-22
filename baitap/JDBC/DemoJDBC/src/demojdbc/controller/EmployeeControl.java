/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojdbc.controller;

import demojdbc.DataConnection;
import demojdbc.controller.dao.DAOEmployee;
import demojdbc.model.Employee;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import demojdbc.view.EmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author a
 */
public class EmployeeControl  implements ActionListener{
    private EmployeeView view;
    private DAOEmployee dao;
    private List employees;

    public EmployeeControl(EmployeeView view) {
        try {
            dao = new DAOEmployee(DataConnection.getConnection());
            System.out.println("Get connection ");
            this.view = view;
            
            this.view.getInsertButton().addActionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
            
        } catch (Throwable ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        getEmployees();
    }
    
    public void getEmployees(){
        List<Employee> emps = dao.getAll();
        for(Employee e: emps){
            this.view.modelEmployee.addRow(new Object[]{e.getEmp_id(), e.getEmp_name(), e.getHire_date(), e.getJob(), e.getSalary(), e.getDept_id(), e.getMng_id(), e.getEmp_no()});
        }
    }
    
    public Employee getEmployeeField(){
        String id = this.view.getIDField().getText();
        String name = this.view.getNameField().getText();
        String hireDate = this.view.getHỉreDateField().getText();
        String job = this.view.getJobField().getText();
        String salary = this.view.getSalaryField().getText();
        String deptId = this.view.getDeptIDField().getText();
        String mngId = this.view.getMngIDField().getText();
        String no = this.view.getEmpNoField().getText();
        System.out.println(id);
        Employee e = new Employee(Integer.parseInt(id), name, no, hire_dat, job, Float.parseFloat(salary), Integer.parseInt(deptId), Integer.parseInt(mngId), null);
        return e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.view.getInsertButton()){
            System.out.println("ok");
            
        }
    }
}
