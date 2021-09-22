/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author vhg_vip
 */
public class CalculatorControl implements ActionListener{
    CalculatorModel model;
    CalculatorView view;

    public CalculatorControl(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        view.addBtn.addActionListener(this);
        view.subtractBtn.addActionListener(this);
        view.multiplyBtn.addActionListener(this);
        view.divideBtn.addActionListener(this);
        view.equalsBtn.addActionListener(this);
        view.resetBtn.addActionListener(this);
        view.oneBtn.addActionListener(this);
        view.twoBtn.addActionListener(this);
        view.threeBtn.addActionListener(this);
        view.fourBtn.addActionListener(this);
        view.fiveBtn.addActionListener(this);
        view.sixBtn.addActionListener(this);
        view.sevenBtn.addActionListener(this);
        view.eightBtn.addActionListener(this);
        view.nineBtn.addActionListener(this);
        view.zeroBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == view.addBtn){
                model.setText(view.addBtn, view.textField);
                view.setTextValue("");
            }
            else if(e.getSource() == view.subtractBtn){
                model.setText(view.subtractBtn, view.textField);
                view.setTextValue("");
            }
            else if(e.getSource() == view.divideBtn){
                model.setText(view.divideBtn, view.textField);
                view.setTextValue("");
            }
            else if(e.getSource() == view.multiplyBtn){
                model.setText(view.multiplyBtn, view.textField);
                view.setTextValue("");
            }
            else if(e.getSource() == view.equalsBtn)
            {
                model.setText(view.equalsBtn, view.textField);
                int res = model.getSum();
                view.setTextValue(res+" ");
            }
            else if(e.getSource() == view.resetBtn)
            {
                model.setText(view.resetBtn, view.textField);
                view.setTextValue("");
            }
            else if(e.getSource() == view.oneBtn)
            {
                String tmp = view.getTextFieldValue() + "1";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
                
            }
            else if(e.getSource() == view.twoBtn)
            {
                String tmp = view.getTextFieldValue() + "2";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);

            }
            else if(e.getSource() == view.threeBtn)
            {
                String tmp = view.getTextFieldValue() + "3";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.fourBtn)
            {
                String tmp = view.getTextFieldValue() + "4";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.fiveBtn)
            {
                String tmp = view.getTextFieldValue() + "5";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.sixBtn)
            {
                String tmp = view.getTextFieldValue() + "6";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.sevenBtn)
            {
                String tmp = view.getTextFieldValue() + "7";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.eightBtn)
            {
                String tmp = view.getTextFieldValue() + "8";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
            else if(e.getSource() == view.nineBtn)
            {
                String tmp = view.getTextFieldValue() + "9";
                model.setNumber(Integer.parseInt(tmp));
                view.setTextValue(tmp);
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }
    
    
}
