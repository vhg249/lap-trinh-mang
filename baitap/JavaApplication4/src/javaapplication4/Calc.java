package javaapplication4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calc extends JFrame implements ActionListener {

    JTextField jtx;
    double temp, temp1, result, a;
    static double m1, m2;
    int k = 1, x = 0, y = 0, z = 0;
    char ch;
    JButton one, two, three, four, five, six, seven, eight, nine, zero, clr;
    JButton plus, min, div, mul, eq, plmi, poin;
//    JMenuBar bar;

//    JRadioButtonMenuItem standard, scientific;
//    JSeparator jp;
//    ButtonGroup bg;
    Container cont;
    JPanel textPanel, buttonpanel;

    Calc() {
        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        JPanel textpanel = new JPanel();
        Font font = new Font("Arial", Font.PLAIN, 18);
        jtx = new JTextField(25);
        jtx.setFont(font);
        jtx.setHorizontalAlignment(SwingConstants.RIGHT);
        jtx.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent keyevent) {
                char c = keyevent.getKeyChar();
                if (c >= '0' && c <= '9') {
                } else {
                    keyevent.consume();
                }
            }
        });
        textpanel.add(jtx);
        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(5, 4, 2, 2));
//        bar = new JMenuBar();

//        standard = new JRadioButtonMenuItem("Standard", true);
//        standard.setMnemonic('S');standard

//        jp = new JSeparator();
//        exit = new JMenuItem("Exit");
//        standard.setMnemonic('E');
//        exit.addActionListener(this);
//        bg = new ButtonGroup();
//        bg.add(standard);

        seven = new JButton("7");
        buttonpanel.add(seven);
        seven.addActionListener(this);
        eight = new JButton("8");
        buttonpanel.add(eight);
        eight.addActionListener(this);
        nine = new JButton("9");
        buttonpanel.add(nine);
        nine.addActionListener(this);
        plus = new JButton("+");
        buttonpanel.add(plus);
        plus.addActionListener(this);
        four = new JButton("4");
        buttonpanel.add(four);
        four.addActionListener(this);
        five = new JButton("5");
        buttonpanel.add(five);
        five.addActionListener(this);
        six = new JButton("6");
        buttonpanel.add(six);
        six.addActionListener(this);
        min = new JButton("-");
        buttonpanel.add(min);
        min.addActionListener(this);
        one = new JButton("1");
        buttonpanel.add(one);
        one.addActionListener(this);
        two = new JButton("2");
        buttonpanel.add(two);
        two.addActionListener(this);
        three = new JButton("3");
        buttonpanel.add(three);
        three.addActionListener(this);
        mul = new JButton("*");
        buttonpanel.add(mul);
        mul.addActionListener(this);
        poin = new JButton(".");
        buttonpanel.add(poin);
        poin.addActionListener(this);
        zero = new JButton("0");
        buttonpanel.add(zero);
        zero.addActionListener(this);
        plmi = new JButton("+/-");
        buttonpanel.add(plmi);
        plmi.addActionListener(this);
        div = new JButton("/");
        div.addActionListener(this);
        buttonpanel.add(div);
        eq = new JButton("=");
        buttonpanel.add(eq);
        eq.addActionListener(this);
        clr = new JButton("AC");
        buttonpanel.add(clr);
        clr.addActionListener(this);

        cont.add("Center", buttonpanel);
        cont.add("North", textpanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    class radiohandler implements ItemListener {
//        public void itemStateChanged(ItemEvent ie) {
//            AbstractButton button = (AbstractButton) ie.getItem();
//            String label = button.getText();
//            {
//                if (label.equals("Standard")) {
//                    validate();
//                }
//            }
//        }
//    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Exit")) {
            System.exit(0);
        }
        if (s.equals("1")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "1");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "1");
                z = 0;
            }
        }
        if (s.equals("2")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "2");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "2");
                z = 0;
            }
        }
        if (s.equals("3")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "3");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "3");
                z = 0;
            }
        }
        if (s.equals("4")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "4");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "4");
                z = 0;
            }
        }
        if (s.equals("5")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "5");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "5");
                z = 0;
            }
        }
        if (s.equals("6")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "6");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "6");
                z = 0;
            }
        }
        if (s.equals("7")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "7");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "7");
                z = 0;
            }
        }
        if (s.equals("8")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "8");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "8");
                z = 0;
            }
        }
        if (s.equals("9")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "9");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "9");
                z = 0;
            }
        }
        if (s.equals("0")) {
            if (z == 0) {
                jtx.setText(jtx.getText() + "0");
            } else {
                jtx.setText("");
                jtx.setText(jtx.getText() + "0");
                z = 0;
            }
        }
        if (s.equals("AC")) {
            jtx.setText("");
            x = 0;
            y = 0;
            z = 0;
        }

        if (s.equals("+/-")) {
            if (x == 0) {
                jtx.setText("-" + jtx.getText());
                x = 1;
            } else {
                jtx.setText(jtx.getText());
            }
        }
        if (s.equals(".")) {
            if (y == 0) {
                jtx.setText(jtx.getText() + ".");
                y = 1;
            } else {
                jtx.setText(jtx.getText());
            }
        }
        if (s.equals("+")) {
            if (jtx.getText().equals("")) {
                jtx.setText("");
                temp = 0;
                ch = '+';
            } else {
                temp = Double.parseDouble(jtx.getText());
                jtx.setText("");
                ch = '+';
                y = 0;
                x = 0;
            }
            jtx.requestFocus();
        }
        if (s.equals("-")) {
            if (jtx.getText().equals("")) {
                jtx.setText("");
                temp = 0;
                ch = '-';
            } else {
                x = 0;
                y = 0;
                temp = Double.parseDouble(jtx.getText());
                jtx.setText("");
                ch = '-';
            }
            jtx.requestFocus();
        }
        if (s.equals("/")) {
            if (jtx.getText().equals("")) {
                jtx.setText("");
                temp = 1;
                ch = '/';
            } else {
                x = 0;
                y = 0;
                temp = Double.parseDouble(jtx.getText());
                ch = '/';
                jtx.setText("");
            }
            jtx.requestFocus();
        }
        if (s.equals("*")) {
            if (jtx.getText().equals("")) {
                jtx.setText("");
                temp = 1;
                ch = '*';
            } else {
                x = 0;
                y = 0;
                temp = Double.parseDouble(jtx.getText());
                ch = '*';
                jtx.setText("");
            }
            jtx.requestFocus();
        }

        if (s.equals("=")) {
            if (jtx.getText().equals("")) {
                jtx.setText("");
            } else {
                temp1 = Double.parseDouble(jtx.getText());
                switch (ch) {
                    case '+':
                        result = temp + temp1;
                        break;
                    case '-':
                        result = temp - temp1;
                        break;
                    case '/':
                        result = temp / temp1;
                        break;
                    case '*':
                        result = temp * temp1;
                        break;
                }
                jtx.setText("");
                jtx.setText(jtx.getText() + result);
                z = 1;
            }
        }
        jtx.requestFocus();
    }

    public static void main(String args[]) {
        Calc n = new Calc();
        n.setTitle("CALCULATOR");
        n.setSize(370, 250);
        n.setResizable(false);
        n.setVisible(true);
    }
}
