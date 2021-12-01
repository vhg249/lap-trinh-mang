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
public class Minute extends Thread{
    private ClockView view;
    private Hour h;
    private int count;

    public Minute(ClockView view, Hour h) {
        super();
        this.view = view;
        this.h = h;
        count = 0;
    }
    
    public void increase(){
        count++;
        if (count==60){
            h.increase();
            count=0;
        }
        view.setMinute(count);
        view.showClock();
    }
    
    public void run(){
        while(true){
            try{
                view.setMinute(count);
                view.showClock();
            }
            catch(Exception e){System.out.print(e.getStackTrace());
            }
        }
    }
}
