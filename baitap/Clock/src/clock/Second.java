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
public class Second extends Thread{
    private ClockView view;
    private Minute m;
    private int count;

    public Second(ClockView view, Minute m) {
        super();
        this.view = view;
        this.m = m;
        count = 0;
    }
    
    public void run(){
        while(true){
            try{
                this.sleep(1000);
                count++;
                if (count==60){
                    count=0;
                    m.increase();
                }
                view.setSecond(count);
                view.showClock();
            }
            catch(Exception e){System.out.print(e.getStackTrace());
            }
        }
    }
}
