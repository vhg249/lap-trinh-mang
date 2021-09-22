/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojdbc.controller.dao;

import demojdbc.model.Employee;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author a
 */
public class DAOEmployee {
    
    private Connection conn;
    private Statement statement;

    public DAOEmployee(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Employee> getAll(){
        List emps = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM employee";
            ResultSet rows = statement.executeQuery(sql);
            int i=0;
            while(rows.next()){
                Employee e = new Employee(
                    rows.getInt("EMP_ID"), 
                    rows.getString("EMP_NAME"),
                    rows.getString("EMP_NO"), 
                    rows.getDate("HIRE_DATE"), 
                    rows.getString("JOB"),
                    rows.getFloat("SALARY"), 
                    rows.getInt("DEPT_ID"), 
                    rows.getInt("MNG_ID"),
                    rows.getBytes("IMAGE")
                );
                emps.add(e);
                i++;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emps;
    }
    
    public boolean insertEmployee(Employee e){
        try {
            String sql = "INSERT INTO employee (EMP_ID, EMP_NAME, EMP_NO, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            
            prepareStatement.setInt(1, e.getEmp_id());
            prepareStatement.setString(2, e.getEmp_name());
            prepareStatement.setString(3, e.getEmp_no());
            prepareStatement.setDate(4, new java.sql.Date(e.getHire_date().getTime()));
            prepareStatement.setString(5, e.getJob());
            prepareStatement.setFloat(6, e.getSalary());
            prepareStatement.setInt(7, e.getDept_id());
            prepareStatement.setInt(8, e.getMng_id());
            
            boolean res = prepareStatement.execute();
            return res;
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        return false;
    }
}
