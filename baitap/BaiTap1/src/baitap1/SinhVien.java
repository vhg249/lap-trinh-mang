/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.Serializable;

/**
 *
 * @author vhg_vip
 */
public class SinhVien implements Serializable{
    private String masv, hoten;
    private int nhom;

    public SinhVien(String masv, String hoten, int nhom) {
        this.masv = masv;
        this.hoten = hoten;
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

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }
    
    public Object[] toObjects(){
        return new Object[]{masv,hoten,nhom};
    }
}
