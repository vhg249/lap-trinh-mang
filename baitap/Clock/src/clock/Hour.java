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
public class Hour extends Thread{
    private ClockView view;
    private int count;

    public Hour(ClockView view) {
        super();
        this.view = view;
        this.count = 0;
    }
    
    public void increase(){
        count++;
        view.setHour(count);
        view.showClock();
    }
    
    public void run(){
        while(true){
            try{
                view.setHour(count);
                view.showClock();
            }
            catch(Exception e){System.out.print(e.getStackTrace());
            }
        }
    }
}
