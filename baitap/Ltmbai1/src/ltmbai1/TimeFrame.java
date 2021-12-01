package ltmbai1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author ABC
 */
public class TimeFrame extends Frame{
    private Label lblTime;
    private int hour=0, minute=0, second=0;
    
    public TimeFrame(){    
        super("Time Counter");

        this.setSize(250, 200);
        this.setResizable(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        lblTime = new Label(hour + ": " + minute + ": " + second);
        lblTime.setSize(300, 300);
        lblTime.setFont(new Font("Tahoma", 1, 24));
        lblTime.setLocation(75,10);
        this.add(lblTime);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    
    public void setHour(int hour){
        this.hour = hour;
        lblTime.setText(hour+" : "+minute+" : "+second);
    }
    
    public void setMinute(int minute){
        this.minute = minute;
        lblTime.setText(hour+" : "+minute+" : "+second);
    }
    
    public void setSecond(int second){
        this.second = second;
        lblTime.setText(hour+" : "+minute+" : "+second);
    }
}
