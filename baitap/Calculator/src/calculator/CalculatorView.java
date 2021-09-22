/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vhg_vip
 */
public class CalculatorView extends JFrame implements ActionListener{
    
    JButton oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn,sevenBtn, eightBtn, nineBtn, zeroBtn;
    JButton addBtn, subtractBtn, multiplyBtn, divideBtn, resetBtn, equalsBtn;
    
    JTextField textField;
    JFrame frame;
    JPanel numbersPanel, signsPanel;

    public CalculatorView() {
        frame = new JFrame("Calculator");
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        
        Font font = new Font("Arial", Font.PLAIN, 18);
        textField = new JTextField(25);
        textField.setFont(font);
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent keyevent) {
                char c = keyevent.getKeyChar();
                if (c >= '0' && c <= '9') {
                } else {
                    keyevent.consume();
                }
            }
        });
        JPanel numbersPanel = new JPanel();
        JPanel signsPanel = new JPanel();
        numbersPanel = numberPan(numbersPanel); 
        
        frame.add(textField, BorderLayout.PAGE_START);
        frame.add(numbersPanel, BorderLayout.CENTER);
        frame.add(signsPanel, BorderLayout.PAGE_END);
        
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    JPanel numberPan(JPanel panel){
        oneBtn = new JButton("1");
        twoBtn = new JButton("2");
        threeBtn = new JButton("3");
        fourBtn= new JButton("4");
        fiveBtn= new JButton("5");
        sixBtn= new JButton("6");
        sevenBtn= new JButton("7");
        eightBtn= new JButton("8");
        nineBtn= new JButton("9");
        zeroBtn= new JButton("0");
        
        addBtn= new JButton("+");
        addBtn.setPreferredSize(new Dimension(10,10));
        subtractBtn= new JButton("-");
        multiplyBtn= new JButton("*");
        divideBtn= new JButton("/");

        resetBtn= new JButton("C");
        equalsBtn= new JButton("=");

        panel.add(sevenBtn);
        panel.add(eightBtn);
        panel.add(nineBtn);
        
        panel.add(addBtn);
        
        panel.add(fourBtn);
        panel.add(fiveBtn);
        panel.add(sixBtn);
        panel.add(subtractBtn);
        
        panel.add(oneBtn);
        panel.add(twoBtn);
        panel.add(threeBtn);
        panel.add(multiplyBtn);
        
        panel.add(resetBtn);
        panel.add(zeroBtn);
        panel.add(equalsBtn);
        panel.add(divideBtn);
        
        panel.setLayout(new GridLayout(5,4));

        return panel;
    }
    
    void setTextValue(String value){
        textField.setText(value);
    }

    String getTextFieldValue(){
        String value = textField.getText();
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
