/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author a
 */
public class Student implements Serializable{
    static final long serialVersionUID = 13L;
    private String masv;
    private String hoten;
    private String IP;
    private int nhom;

    public Student() {
    }

    public Student(String masv, String hoten, String IP, int nhom) {
        this.masv = masv;
        this.hoten = hoten;
        this.IP = IP;
        this.nhom = nhom;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }
    
}
