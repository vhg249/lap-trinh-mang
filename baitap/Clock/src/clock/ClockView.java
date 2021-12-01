/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author a
 */
public class ClockView extends JFrame{
    private Label clockLabel;
    private int hour=0, minute=0, second=0;

    public ClockView() {
        this.setSize(250, 200);
        this.setResizable(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        clockLabel = new Label("");
        showClock();
        clockLabel.setSize(400, 400);
        clockLabel.setFont(new Font("Tahoma", 1, 36));
        clockLabel.setLocation(20,-120);
        this.add(clockLabel);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    
    
    
    public void setHour(int hour){
        this.hour = hour;
    }
    
    public void setMinute(int minute){
        this.minute = minute;
    }
    
    public void setSecond(int second){
        this.second = second;
    }
    
    public void showClock(){
        String h, m, s;
        if(hour< 10) h = "0" + hour;
        else h = "" + hour;
        if(minute < 10) m = "0" + minute;
        else m = "" + minute;
        if(second < 10) s = "0" + second;
        else s = "" + second;
        clockLabel.setText(h + " : " + m + " : " + s);
    }
}
