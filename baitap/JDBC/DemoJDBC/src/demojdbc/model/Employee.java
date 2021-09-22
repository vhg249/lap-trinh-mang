/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demojdbc.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author a
 */
public class Employee implements Serializable {
    private int emp_id;
    private String emp_name;
    private String emp_no;
    private Date hire_date;
    private String job;
    private float salary;
    private int dept_id;
    private int mng_id;
    private byte[] image;

    public Employee(int emp_id, String emp_name, String emp_no, Date hire_date, String job, float salary, int dept_id, int mng_id, byte[] image) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_no = emp_no;
        this.hire_date = hire_date;
        this.job = job;
        this.salary = salary;
        this.dept_id = dept_id;
        this.mng_id = mng_id;
        this.image = image;
    }

    public Employee() {
    }
    
    public Object[] toObjects(){
        return new Object[]{emp_id, emp_name, emp_no, hire_date, job, salary, dept_id, mng_id, image};
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getMng_id() {
        return mng_id;
    }

    public void setMng_id(int mng_id) {
        this.mng_id = mng_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
