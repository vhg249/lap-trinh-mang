/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author vhg_vip
 */
public class CalculatorModel {
    private int sum;
    private int number = 0;
    private String operation;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CalculatorModel() {
        sum = 0;
        number = 0;
    }
    
    public void setText(JButton btn, JTextField field){
        String tmp = btn.getText();
        if(tmp == "="){
//            calculate(operation);
        }
        else if(tmp == "C"){
            operation = tmp;
        }
        else{
            operation = tmp;
            this.number = Integer.parseInt(field.getText());
              
        }
        calculate(operation);
    }
    
    void calculate(String sign){
        switch(sign){
            case "+":
                sum += number;
                setSum(sum);
                break;
            case "-":
                if(sum == 0){
                    sum += number;
                }
                else{
                   sum -= number; 
                }
                setSum(sum);
                break;
            case "*":
                if(sum == 0){
                    sum += number;
                }
                else{
                    sum *= number;
                }
                setSum(sum);
                break;
            case "/":
                if(sum==0) sum+=number;
                else sum /= number;
                setSum(sum);
                break;
            case "=":
                setSum(sum);
                break;
            case "C":
                this.operation = "+";
                sum = 0;
                number = 0;
                setSum(sum);
                setNumber(number);
                break;
            default:
        }
    }
}
