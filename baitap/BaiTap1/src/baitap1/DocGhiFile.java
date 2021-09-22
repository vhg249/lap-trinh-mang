/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitap1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vhg_vip
 */
public class DocGhiFile {
    public static void GhiFile(String tenFile, List ds){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tenFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Object o: ds){
                oos.writeObject(o);
            }
            oos.close();
        } catch (Exception e) {
        }
    }
    
    public static List DocFile(String tenFile){
        FileInputStream fis = null;
        List ds = new ArrayList();
        try {
            fis = new FileInputStream(tenFile);
        } catch (Exception e) {
        }
        
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                Object o = ois.readObject();
                if(o == null) break;
                ds.add(o);
            }
            ois.close();
        } catch (Exception e) {
        }
        return ds;
    }
}
