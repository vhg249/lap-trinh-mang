/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

/**
 *
 * @author a
 */
public class Clock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClockView clock = new ClockView();
        clock.setVisible(true);
        
        Hour h = new Hour(clock);
        Minute m = new Minute(clock, h);
        Second s = new Second(clock, m);
        
        h.start();
        m.start();
        s.start();
    }
    
}
