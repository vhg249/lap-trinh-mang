/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vhg_vip
 */
public class BaiTap1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("1. Them sinh vien");
        System.out.println("2. Hien thi");
        
        Scanner sc = new Scanner(System.in);
        String tenFile = "src/baitap1/DanhSach.txt";
        List list = new ArrayList();
        list = DocGhiFile.DocFile(tenFile);
        while(true){
            int choose = Integer.parseInt(sc.nextLine());
            switch(choose){
                case 1:
                    System.out.println("Nhap ma sinh vien: ");
                    String masv = sc.nextLine();
                    System.out.println("Nhap ho ten: ");
                    String ten = sc.nextLine();
                    System.out.println("Nhap nhom: ");
                    int nhom = Integer.parseInt(sc.nextLine());
                    SinhVien sv = new SinhVien(masv, ten, nhom);
                    list.add(sv);
                    DocGhiFile.GhiFile(tenFile, list);
                    break;
                case 2:
                    for(Object a: list){
                        SinhVien s = (SinhVien)a;
                        System.out.println(s.getMasv() + " " + s.getHoten() + " " + s.getNhom());
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
}
