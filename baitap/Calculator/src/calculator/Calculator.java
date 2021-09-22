/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author vhg_vip
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalculatorView view = new CalculatorView();
        CalculatorModel model = new CalculatorModel();
        
        new CalculatorControl(model, view);
    }
    
}
