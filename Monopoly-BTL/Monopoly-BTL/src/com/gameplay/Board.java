package com.gameplay;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import com.OpeningWindow;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Board implements Initializable {

    @FXML
    Label money, p1label, p2label, p3label, p4label, t1, t2;

    @FXML
    Pane p1, p2, p3, p4;

    @FXML
    VBox vbox;

    @FXML
    Circle nil_guti, lal_guti, sobuj_guti, holud_guti, guti;

    @FXML
    GridPane grid;

    @FXML
    ImageView dice1, dice2, dolilShower;

    @FXML
    Button rollButton;

    @FXML
    ListView<String> list;

    int tk = 500;

    final int interval = 170;
    int numPlr;
    int pos[];
    String string;
    int serialint;
    boolean jailCommand = false;
    int jail_e_jawar_turn = -1;
    int Dice1, Dice2;
    boolean directly = true;
    int indirectDice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rollButton.setDisable(true);

        numPlr = OpeningWindow.getgc().getMatchPlayers();
        pos = new int[numPlr];
        for (int i = 0; i < numPlr; i++) {
            pos[i] = 0;
        }

        // System.out.println(numPlr);
        if (numPlr == 2) {
            sobuj_guti.setVisible(false);
            holud_guti.setVisible(false);
            p3.setVisible(false);
            p4.setVisible(false);
        }
        if (numPlr == 3) {
            holud_guti.setVisible(false);
            p4.setVisible(false);
        }

        showtime();
        Thread t = new Thread(inputFromServer);
        t.setDaemon(true);
        t.start();

    }

    public Board() {
    }

    public void rollDice(int dice_1, int dice_2, Circle guti, int ii) {

        int dice = dice_1 + dice_2;
        RotateTransition rt1 = new RotateTransition(Duration.seconds(0.25));
        RotateTransition rt2 = new RotateTransition(Duration.seconds(0.25));

        rt1.setNode(dice1);
        rt1.setByAngle(360);
        rt1.setFromAngle(0);
        rt2.setNode(dice2);
        rt2.setByAngle(360);
        rt2.setFromAngle(0);

        rt1.setCycleCount(3);
        rt1.setAutoReverse(true);
        rt2.setCycleCount(3);
        rt2.setAutoReverse(true);

        rt1.play();
        rt2.play();

        rt2.setOnFinished(e -> {

            if (dice_1 == 1)
                dice1.setImage(new Image("file:d1.jpg"));
            else if (dice_1 == 2)
                dice1.setImage(new Image("file:d2.jpg"));
            else if (dice_1 == 3)
                dice1.setImage(new Image("file:d3.jpg"));
            else if (dice_1 == 4)
                dice1.setImage(new Image("file:d4.jpg"));
            else if (dice_1 == 5)
                dice1.setImage(new Image("file:d5.jpg"));
            else
                dice1.setImage(new Image("file:d6.jpg"));

            if (dice_2 == 1)
                dice2.setImage(new Image("file:d1.jpg"));
            else if (dice_2 == 2)
                dice2.setImage(new Image("file:d2.jpg"));
            else if (dice_2 == 3)
                dice2.setImage(new Image("file:d3.jpg"));
            else if (dice_2 == 4)
                dice2.setImage(new Image("file:d4.jpg"));
            else if (dice_2 == 5)
                dice2.setImage(new Image("file:d5.jpg"));
            else
                dice2.setImage(new Image("file:d6.jpg"));

            rt1.setByAngle(0);
            rt1.setFromAngle(360);
            rt2.setByAngle(0);
            rt2.setFromAngle(360);

            rt1.setCycleCount(1);
            rt2.setCycleCount(1);

            rt1.play();
            rt2.play();

            rt2.setOnFinished(event -> doTask(guti, ii, dice));

        });

    }

    public void rollDiceClicked() {

        rollButton.setDisable(true);

        Random rand = new Random();

        int dice_1 = rand.nextInt(6) + 1;
        int dice_2 = rand.nextInt(6) + 1;

        int dice = dice_1 + dice_2;
        System.out.println("=> " + dice);

        sendmsg2server("rolldice#" + String.valueOf(dice_1) + "#" + String.valueOf(dice_2));

    }

    public void goToNextProperty(Circle guti, int ii) {

        if (pos[ii] >= 0 && pos[ii] < 10) {
            int x = 9 - pos[ii];
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, x, 10);
        } else if (pos[ii] > 9 && pos[ii] < 20) {
            int x = 19 - pos[ii];
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, 0, x);
        } else if (pos[ii] > 19 && pos[ii] < 30) {
            int x = pos[ii] - 19;
            pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, x, 0);
        } else if (pos[ii] > 29 && pos[ii] < 40) {
            int x = pos[ii] - 29;
            if (pos[ii] == 39)
                pos[ii] = (0);
            else
                pos[ii] = (pos[ii] + 1);
            grid.getChildren().remove(guti);
            grid.add(guti, 10, x);
        }
    }

    public void jaile_ja(Circle guti, int ii) {
        pos[ii] = 10;
        grid.getChildren().remove(guti);
        grid.add(guti, 0, 10);
    }

    public void p1() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P1.png"));
    }

    public void p2() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P2.png"));
    }

    public void p3() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P3.png"));
    }

    public void p4() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P4.png"));
    }

    public void p5() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P5.png"));
    }

    public void p6() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P6.png"));
    }

    public void p7() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P7.png"));
    }

    public void p8() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P8.png"));
    }

    public void p9() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P9.png"));
    }

    public void p10() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P10.png"));
    }

    public void p11() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P11.png"));
    }

    public void p12() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P12.png"));
    }

    public void p13() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P13.png"));
    }

    public void p14() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P14.png"));
    }

    public void p15() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P15.png"));
    }

    public void p16() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P16.png"));
    }

    public void p17() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P17.png"));
    }

    public void p18() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P18.png"));
    }

    public void p19() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P19.png"));
    }

    public void p20() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P20.png"));
    }

    public void p21() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P21.png"));
    }

    public void p22() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:P22.png"));
    }

    public void s1() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:S1.png"));
    }

    public void s2() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:S2.png"));
    }

    public void s3() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:S3.png"));
    }

    public void s4() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:S4.png"));
    }

    public void u1() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:U1.png"));
    }

    public void u2() {
        list.setVisible(false);
        dolilShower.setImage(new Image("file:U2.png"));
    }

    Task<Void> inputFromServer = new Task<Void>() {
        @Override
        protected Void call() {
            while (true) {
                string = "";
                try {
                    string = OpeningWindow.getgc().getInFromServer().readLine();
                    System.out.println("string:  " + string);
                    if (string.contains("confirm")) {
                        System.out.println("successful");
                        {
                            String newmsg[] = new String[4];
                            newmsg = string.split("#");

                            String serial = newmsg[0];
                            serialint = Integer.parseInt(serial);

                            serialint--;
                            Dice1 = Integer.parseInt(newmsg[2]);
                            Dice2 = Integer.parseInt(newmsg[3]);
                            System.out.println("dice1 " + Dice1 + " dice2 " + Dice2);

                            System.out.println("serial ceck " + serial.equals("1"));
                            if (serial.equals("1"))
                                rollDice(Dice1, Dice2, nil_guti, serialint);
                            else if (serial.equals("2"))
                                rollDice(Dice1, Dice2, lal_guti, serialint);
                            else if (serial.equals("3"))
                                rollDice(Dice1, Dice2, sobuj_guti, serialint);
                            else if (serial.equals("4"))
                                rollDice(Dice1, Dice2, holud_guti, serialint);

                        }
                    }

                    else if (string.startsWith("enableStartPlaying")) {
                        int serialInt = Integer.parseInt(string.substring(string.length() - 1));
                        serialInt++;
                        OpeningWindow.getgc().setSerial(serialInt);
                        System.out.println("game e serial " + serialInt);
                        if (serialInt == 1)
                            rollButton.setDisable(false);
                    }

                    else if (string.equals("You are in jail. pay fee.")) {
                        rollButton.setDisable(true);
                        alertbox(1, "You Have To Pay BDT 50 To Get Out Of Jail");
                        jail_e_jawar_turn = -1;
                    }

                    else if (string.contains("jail immediately")) {
                        jailCommand = true;
                        jail_e_jawar_turn = Integer.parseInt(string.substring(0, 1));
                        if (jail_e_jawar_turn == OpeningWindow.getgc().getSerial())
                            sendmsg2server("ok gelam :(");
                    }

                    else if (string.equals("you are free !!")) {
                        sendmsg2server("yesss im free");
                        rollButton.setDisable(false);
                    }

                    else if (string.equals("Other enable")) {
                        if (serialint == (OpeningWindow.getgc().getSerial() + numPlr - 2) % numPlr)
                            rollButton.setDisable(false);
                    }

                    else if (string.equals("paytax1")) {
                        alertbox(Dice1 + Dice2, "You Have To Pay Income Tax");
                    }

                    else if (string.equals("paytax2")) {
                        alertbox(Dice1 + Dice2, "You Have To Pay Luxury Tax");
                    }

                    else if (string.equals("pay rent")) {
                        if (directly == false) {
                            alertbox(indirectDice, "You Have To Pay Rent For This Property");
                            directly = true;
                        } else
                            alertbox(Dice1 + Dice2, "You Have To Pay Rent For This Property");
                    }

                    else if (string.contains("buy this property")) {
                        String x = string.substring("buy this property".length());

                        StringBuffer sb = new StringBuffer("Do You Want To Buy This Property?");
                        sb.append(x);
                        String s = sb.toString();

                        if (directly == false) {
                            alertbox(indirectDice, s);
                            directly = true;
                        } else {
                            alertbox(Dice1 + Dice2, s);
                        }
                    }

                    else if (string.contains("fortune") || string.contains("opportunity")) {
                        alertbox(Dice1 + Dice2, string);
                    }

                    else if (string.equals("Start next round")) {
                        alertbox(Dice1 + Dice2, string);
                        directly = false;
                        indirectDice = 1;
                    }

                    else if (string.contains("someplace")) {
                        indirectDice = Integer.parseInt(string.substring(21));
                        int ii;
                        if (string.charAt(0) == '1') {
                            guti = nil_guti;
                            ii = 0;
                        } else if (string.charAt(0) == '2') {
                            guti = lal_guti;
                            ii = 1;
                        } else if (string.charAt(0) == '3') {
                            guti = sobuj_guti;
                            ii = 2;
                        } else {
                            guti = holud_guti;
                            ii = 3;
                        }
                        doTask(guti, ii, indirectDice);
                        if (!string.contains("go"))
                            directly = false;
                    }

                    else if (string.equals("Build house?")) {
                        alertbox(Dice1 + Dice2, "Do You Want To Build A House Here?");
                    }

                    else if (string.equals("Build hotel?")) {
                        alertbox(Dice1 + Dice2, "Do You Want To Build A Hotel Here?");
                    }

                    else if (string.equals("Nothing")) {
                        alertbox(Dice1 + Dice2, "This Is Your Property, But You Cannot Construct Anything Here");
                    }

                    else if (string.contains("takeBonus")) {
                        String newmsg[] = new String[2];
                        newmsg = string.split("#");

                        int tkBonus = Integer.parseInt(newmsg[1]);
                        tk = tk + tkBonus;
                        System.out.println("Bonus: " + tk);
                        updateMoney();

                    }

                    else if (string.contains("takeFine")) {
                        String newmsg[] = new String[2];
                        newmsg = string.split("#");

                        int takeFine = Integer.parseInt(newmsg[1]);
                        tk = tk - takeFine;
                        System.out.println("Fine: " + tk);

                        updateMoney();
                    }

                    else if (string.contains("Winner player")) {
                        alertbox(Dice1 + Dice2, string);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("client side resources:");
                for (int i = 0; i < 28; i++) {
                    if (OpeningWindow.getgc().getMySquares().elementAt(i) == 1) {
                        System.out.println(OpeningWindow.getgc().getSquareName().elementAt(i));
                    }
                }
            }

        }

    };

    public void sendmsg2server(String msg) {
        OpeningWindow.getgc().getOutToServer().println(msg);
        OpeningWindow.getgc().getOutToServer().flush();
    }

    public void doTask(Circle guti, int ii, int dice) {

        System.out.println("Running Task");

        Thread th = new Thread(new Task<String>() {

            @Override
            protected String call() throws Exception {

                Thread.sleep(200);

                for (int i = 0; i < dice; i++) {
                    Thread.sleep(interval);
                    Platform.runLater(() -> goToNextProperty(guti, ii));
                }

                if (jailCommand == true) {
                    jailCommand = false;
                    Thread.sleep(1018); // 11701018
                    Platform.runLater(() -> jaile_ja(guti, ii));

                }

                return "The return type of the task is different";
            }
        });

        th.setDaemon(true);
        th.start();
    }

    public void alertbox(int d, String msg) {
        System.out.println("Running Task");
        AlertBox ab = new AlertBox();
        Thread th = new Thread(new Task<String>() {

            @Override
            protected String call() throws Exception {
                Thread.sleep(1500 + d * interval);
                Platform.runLater(() -> ab.display("Confirmation Box", msg));

                return "The return type of the task is different";
            }
        });

        th.setDaemon(true);
        th.start();

    }

    public void mypropsclicked() {

        list.getItems().clear();
        for (int i = 0; i < 28; i++) {
            if (OpeningWindow.getgc().getMySquares().elementAt(i) == 1) {
                list.getItems().add(OpeningWindow.getgc().getSquareName().elementAt(i));
            }
        }
        list.setVisible(true);

    }

    public void updateMoney() {

        Thread th = new Thread(new Task<String>() {

            @Override
            protected String call() throws Exception {
                System.out.println("              " + tk);
                Platform.runLater(() -> money.setText(String.valueOf(tk)));

                return "The return type of the task is different";
            }
        });

        th.setDaemon(true);
        th.start();

    }

    public void showtime() {
        int time = PlayOptions1.getTime();

        if (time == 0)
            return;

        else if (time == 1) {
            t1.setVisible(true);

            Thread t = new Thread(new Task<String>() {

                @Override
                protected String call() throws Exception {

                    int total = 1800;

                    while (total >= 0) {
                        int min = total / 60, sec = total % 60;
                        String minute, second;
                        if (min < 10)
                            minute = "0" + String.valueOf(min);
                        else
                            minute = String.valueOf(min);

                        if (sec < 10)
                            second = "0" + String.valueOf(sec);
                        else
                            second = String.valueOf(sec);

                        Thread.sleep(1000);
                        Platform.runLater(() -> t2.setText(minute + ":" + second));

                        total--;
                    }
                    sendmsg2server("Game over");

                    return null;
                }
            });

            t2.setVisible(true);
            t.setDaemon(true);
            t.start();
        }

    }

}