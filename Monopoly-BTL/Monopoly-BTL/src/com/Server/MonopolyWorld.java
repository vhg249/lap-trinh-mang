package com.Server;

import java.io.*;
import java.util.*;

public class MonopolyWorld implements Runnable {

    private Property properties[];
    private Station stations[];
    private Utility utilities[];
    private String board[];
    private Player players[];
    private int numPlayer;
    private int matchID;
    private String mode;
    private int playerIDwholelist[];
    private Vector<Integer> fortune;
    private Vector<Integer> opportunity;

    public MonopolyWorld(int n, int matchID, int arr[], String mode) {
        numPlayer = n;
        this.matchID = matchID;
        playerIDwholelist = arr;
        this.mode = mode;
        fortune = new Vector<>();
        opportunity = new Vector<>();

        for (int i = 0; i < 12; i++) {
            fortune.add(i + 1);
        }

        for (int i = 0; i < 14; i++) {
            opportunity.add(i + 1);
        }

        Collections.shuffle(fortune);
        Collections.shuffle(opportunity);

    }

    public void initializeTheBoard() {
        properties = new Property[22];

        properties[0] = new Property("Farmgate", 1, -1, 60, 2, false, false, "black", 0, 0, 10, 30, 90, 160, 250, 50,
                50);
        properties[1] = new Property("Shahbag", 2, -2, 60, 4, false, false, "black", 0, 0, 20, 60, 180, 320, 450, 50,
                50);
        properties[2] = new Property("Palashi", 3, -1, 100, 6, false, false, "sky blue", 0, 0, 30, 90, 270, 400, 550,
                50, 50);
        properties[3] = new Property("Azimpur", 4, -2, 100, 6, false, false, "sky blue", 0, 0, 30, 90, 270, 400, 550,
                50, 50);
        properties[4] = new Property("Dhanmondi", 5, -3, 120, 8, false, false, "sky blue", 0, 0, 40, 100, 300, 450, 600,
                50, 50);
        properties[5] = new Property("Tongi", 6, -1, 140, 10, false, false, "pink", 0, 0, 50, 150, 450, 625, 750, 100,
                100);
        properties[6] = new Property("Khilkhet", 7, -2, 140, 10, false, false, "pink", 0, 0, 50, 150, 450, 625, 750,
                100, 100);
        properties[7] = new Property("Shyamoli", 8, -3, 160, 12, false, false, "pink", 0, 0, 60, 180, 500, 700, 900,
                100, 100);
        properties[8] = new Property("Pallobi", 9, -1, 180, 14, false, false, "orange", 0, 0, 70, 200, 550, 750, 950,
                100, 100);
        properties[9] = new Property("Mirpur", 10, -2, 180, 14, false, false, "orange", 0, 0, 70, 200, 550, 750, 950,
                100, 100);
        properties[10] = new Property("Uttara", 11, -3, 200, 16, false, false, "orange", 0, 0, 80, 220, 600, 800, 1000,
                100, 100);
        properties[11] = new Property("Motijheel", 12, -1, 220, 18, false, false, "red", 0, 0, 90, 250, 700, 875, 1050,
                150, 150);
        properties[12] = new Property("Arambagh", 13, -2, 220, 18, false, false, "red", 0, 0, 90, 250, 700, 875, 1050,
                150, 150);
        properties[13] = new Property("Wari", 14, -3, 240, 20, false, false, "red", 0, 0, 100, 300, 750, 925, 1100, 150,
                150);
        properties[14] = new Property("Jatrabari", 15, -1, 260, 22, false, false, "yellow", 0, 0, 110, 330, 800, 975,
                1150, 150, 150);
        properties[15] = new Property("Khilgaon", 16, -2, 260, 22, false, false, "yellow", 0, 0, 110, 330, 800, 975,
                1150, 150, 150);
        properties[16] = new Property("Shantinagar", 17, -3, 280, 24, false, false, "yellow", 0, 0, 120, 360, 850, 1025,
                1200, 150, 150);
        properties[17] = new Property("Baridhara", 18, -1, 300, 26, false, false, "green", 0, 0, 130, 390, 900, 1100,
                1275, 200, 200);
        properties[18] = new Property("Kuril", 19, -2, 300, 26, false, false, "green", 0, 0, 130, 390, 900, 1100, 1275,
                200, 200);
        properties[19] = new Property("Badda", 20, -3, 320, 28, false, false, "green", 0, 0, 150, 450, 1000, 1200, 1400,
                200, 200);
        properties[20] = new Property("Gulshan", 21, -1, 350, 35, false, false, "violet", 0, 0, 175, 500, 1100, 1300,
                1500, 200, 200);
        properties[21] = new Property("Banani", 22, -2, 400, 50, false, false, "violet", 0, 0, 200, 600, 1400, 1700,
                2000, 200, 200);

        stations = new Station[4];
        stations[0] = new Station("Tejgaon", 23, -1, 200, 25, false, false);
        stations[1] = new Station("Airport", 24, -2, 200, 25, false, false);
        stations[2] = new Station("Kamalapur", 25, -3, 200, 25, false, false);
        stations[3] = new Station("Cantonment", 26, -4, 200, 25, false, false);

        utilities = new Utility[2];
        utilities[0] = new Utility("Power Facility", 27, -1, 150, -0, false, false);
        utilities[1] = new Utility("Water Facility", 28, -2, 150, -0, false, false);
    }

    public void assignBoardPosition() {
        board = new String[40];

        for (int i = 0; i < 40; i++) {
            board[i] = "xxx";
        }

        board[2] = board[17] = board[33] = "fortune challenge";
        board[7] = board[22] = board[36] = "get opportunity";

        board[0] = "Start";
        board[10] = "Jail";
        board[30] = "Go to Jail";
        board[20] = "Rest";

        board[4] = "Income Tax";
        board[38] = "Income Tax 2";

        board[5] = stations[0].getName();
        board[15] = stations[1].getName();
        board[25] = stations[2].getName();
        board[35] = stations[3].getName();

        board[12] = utilities[0].getName();
        board[28] = utilities[1].getName();

        for (int i = 0, j = 0; i < board.length; i++) {
            if (board[i].equals("xxx")) {
                board[i] = properties[j].getName();
                j++;
            }
        }

    }

    public void setPlayers() {

        players = new Player[numPlayer];
        for (int i = 0; i < numPlayer; i++) {
            players[i] = new Player(i, "player" + (i + 1), false, 0, 500, 500);
        }
    }

    public void StartCheck(int i, int dice) {
        if (players[i].getPosition() >= 28 && players[i].getPosition() <= 39) {
            if ((players[i].getPosition() + dice) >= 40) {
                System.out.printf("\nExcessive quota\n");
                dm("Start next round", i);
                String s = readClientsMsg(i);
                if (s.equals("ok collect")) {
                    takeFromBank(200, i);
                    dm("takeBonus#200", i);
                }

            }
        }
    }

    public int Fortune(int i) {
        int x = fortune.elementAt(11);

        dm("fortune#" + x, i);
        for (int j = 10; j >= 0; j--) {
            int xx = fortune.elementAt(j);
            fortune.set(j + 1, xx);
        }

        fortune.set(0, x);

        String msg = readClientsMsg(i);
        if (msg.equals("1")) {
            takeFromBank(50, i);

            dm("takeBonus#50", i);

        }

        else if (msg.equals("2") || msg.equals("3")) {
            if (players[i].getPosition() == 2) {
                players[i].setPosition(5);
                bc(String.valueOf(i + 1) + "advance to someplace3");
            }

            else if (players[i].getPosition() == 17) {
                players[i].setPosition(25);
                bc(String.valueOf(i + 1) + "advance to someplace8");
            }

            else if (players[i].getPosition() == 33) {
                players[i].setPosition(35);
                bc(String.valueOf(i + 1) + "advance to someplace2");
            }

        }

        else if (msg.equals("4")) {
            int dist;
            int ip = players[i].getPosition();
            players[i].setPosition(39);
            dist = 39 - ip;
            bc(String.valueOf(i + 1) + "advance to someplace" + String.valueOf(dist));

        }

        else if (msg.equals("5")) {
            int dist;
            int ip = players[i].getPosition();
            players[i].setPosition(5);
            if (ip == 17 || ip == 33)
                dist = 40 - ip + 5;
            else
                dist = 3;
            bc(String.valueOf(i + 1) + "advance to someplace" + String.valueOf(dist));
            if (ip == 17 || ip == 33) {
                dm("Start next round", i);
                String s = readClientsMsg(i);
                if (s.equals("ok collect")) {
                    takeFromBank(200, i);

                    dm("takeBonus#200", i);
                }
            }
        }

        else if (msg.equals("6")) {
            payToBank(15, i, false);
            dm("takeFine#15", i);
        }

        if (msg.equals("7")) {
            takeFromBank(50, i);
            dm("takeBonus#50", i);

        }

        else if (msg.equals("8")) {
            int dist;
            int ip = players[i].getPosition();
            players[i].setPosition(11);
            if (ip == 17 || ip == 33)
                dist = 40 - ip + 11;
            else
                dist = 9;
            bc(String.valueOf(i + 1) + "advance to someplace" + String.valueOf(dist));
            if (ip == 17 || ip == 33) {
                dm("Start next round", i);
                String s = readClientsMsg(i);
                if (s.equals("ok collect")) {
                    takeFromBank(200, i);
                    dm("takeBonus#200", i);
                }
            }
        }

        else if (msg.equals("9")) {
            advanceToGo(i);
        }

        else if (msg.equals("10")) {
            int dist;
            int ip = players[i].getPosition();
            players[i].setPosition(24);
            if (ip == 33)
                dist = 40 - ip + 24;
            else
                dist = 24 - ip;
            bc(String.valueOf(i + 1) + "advance to someplace" + String.valueOf(dist));
            if (ip == 33) {
                dm("Start next round", i);
                String s = readClientsMsg(i);
                if (s.equals("ok collect")) {
                    takeFromBank(200, i);
                    dm("takeBonus#200", i);
                }
            }
        }

        else if (msg.equals("11")) {
            houseHotelRepair(25, 100, i);
        }

        else if (msg.equals("12")) {
            payToBank(50 * (numPlayer - 1), i, false);
            int xx = 50 * (numPlayer - 1);
            dm("takeFine#" + String.valueOf(xx), i);
            for (int j = 0; j < numPlayer; j++) {
                if (j == i)
                    continue;
                else {
                    takeFromBank(50, j);
                    dm("takeBonus#50", j);
                }
            }
        }

        return x;

    }

    public void houseHotelRepair(int a, int b, int i) {
        int hou = 0, hot = 0;
        for (i = 0; i < players[i].getRecords().size(); i++) {
            String sompod = board[players[i].getRecords().elementAt(0)];
            if (arraySearch(sompod, properties) != -1) {
                int idx = arraySearch(sompod, properties);
                hou = hou + properties[idx].getNoOfHouse();
                hot = hot + properties[idx].getNoOfHotel();
            }
        }

        payToBank(a * hou + b * hot, i, false);
        int xx = a * hou + b * hot;
        dm("takeFine#" + String.valueOf(xx), i);
    }

    public void advanceToGo(int i) {
        int dist;
        int ip = players[i].getPosition();
        dist = 40 - ip;
        bc(String.valueOf(i + 1) + "advance go someplace" + String.valueOf(dist));
        players[i].setPosition(0);
        dm("Start next round", i);
        String s = readClientsMsg(i);
        if (s.equals("ok collect")) {
            takeFromBank(200, i);
            dm("takeBonus#200", i);
        }
    }

    public int Opportunity(int i) {
        int x = opportunity.elementAt(13);

        dm("opportunity#" + x, i);
        for (int j = 12; j >= 0; j--) {
            int xx = opportunity.elementAt(j);
            opportunity.set(j + 1, xx);
        }

        opportunity.set(0, x);

        String msg = readClientsMsg(i);
        if (msg.equals("1")) {
            takeFromBank(20, i);
            dm("takeBonus#20", i);
        }

        else if (msg.equals("2")) {
            takeFromBank(100, i);
            dm("takeBonus#100", i);
        }

        else if (msg.equals("3")) {
            takeFromBank(100, i);
            dm("takeBonus#100", i);
        }

        else if (msg.equals("4")) {
            takeFromBank(200, i);
            dm("takeBonus#200", i);
        }

        else if (msg.equals("5")) {

            for (int j = 0; j < numPlayer; j++) {
                if (j == i)
                    continue;
                else {
                    payToBank(50, j, false);
                    dm("takeFine#50", j);
                }
            }
            takeFromBank(50 * (numPlayer - 1), i);
            int xx = 50 * (numPlayer - 1);

            dm("takeBonus#" + String.valueOf(xx), i);
        }

        else if (msg.equals("6")) {
            payToBank(100, i, false);
            dm("takeFine#100", i);
        }

        else if (msg.equals("7")) {
            takeFromBank(45, i);
            dm("takeBonus#45", i);
        }

        else if (msg.equals("8")) {
            payToBank(50, i, false);
            dm("takeFine#50", i);
        }

        else if (msg.equals("9")) {
            houseHotelRepair(40, 115, i);
        }

        else if (msg.equals("10")) {
            payToBank(150, i, false);
            dm("takeFine#150", i);
        }

        else if (msg.equals("11")) {
            takeFromBank(20, i);
            dm("takeBonus#20", i);
        }

        else if (msg.equals("12")) {
            advanceToGo(i);
        }

        else if (msg.equals("13")) {
            takeFromBank(10, i);
            dm("takeBonus#10", i);
        }

        else if (msg.equals("14")) {
            takeFromBank(100, i);
            dm("takeBonus#100", i);

        }

        return x;
    }

    public void payToBank(int tk, int i, boolean isBuying) {
        if (isBuying == false)
            players[i].setAsset(players[i].getAsset() - tk);
        players[i].setCash(players[i].getCash() - tk);
    }

    public void takeFromBank(int tk, int i) {
        players[i].setAsset(players[i].getAsset() + tk);
        players[i].setCash(players[i].getCash() + tk);
    }

    public void goToJail(int i) {
        players[i].setPosition(10);
        players[i].setInJail(true);
    }

    public int arraySearch(String str, Record array[]) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            if (array[i].getName().equalsIgnoreCase(str))
                return i;
        }
        return -1;
    }

    public void payRent(Record dll, int i) {
        int rent = dll.getRent();
        int ownerID = dll.getOwnerID();
        payToBank(rent, i, false);
        dm("takeFine#" + String.valueOf(rent), i);
        takeFromBank(rent, ownerID - 1);
        dm("takeBonus#" + String.valueOf(rent), ownerID - 1);
    }

    public void buy(Record dll, int i, int pos) {
        // if(dll.getPrice() < players[i].getCash()){
        payToBank(dll.getPrice(), i, true);
        dm("takeFine#" + String.valueOf(dll.getPrice()), i);
        dll.setBought(true);
        dll.setOwnerID(i + 1); // parameter e playerIDmatch so plus one
        players[i].getRecords().add(pos);

    }

    public void payRent(Utility ut, int i, int dice) {
        int rent = dice * Utility.getFactor();
        int ownerid = ut.getOwnerID();
        payToBank(rent, i, false);
        dm("takeFine#" + String.valueOf(rent), i);
        takeFromBank(rent, ownerid - 1);
        dm("takeBonus#" + String.valueOf(rent), ownerid - 1);
    }

    public void payRent(Property pr, int i, boolean isProperty) {
        int rent;
        if (pr.getNoOfHotel() == 1)
            rent = pr.getRentHotel();
        else if (pr.getNoOfHouse() == 4)
            rent = pr.getRent4houses();
        else if (pr.getNoOfHouse() == 3)
            rent = pr.getRent3houses();
        else if (pr.getNoOfHouse() == 2)
            rent = pr.getRent2houses();
        else if (pr.getNoOfHouse() == 1)
            rent = pr.getRent1house();
        else
            rent = pr.getRent();

        int ownerid = pr.getOwnerID();
        payToBank(rent, i, false);
        dm("takeFine#" + String.valueOf(rent), i);
        takeFromBank(rent, ownerid - 1);
        dm("takeBonus#" + String.valueOf(rent), ownerid - 1);
    }

    public boolean buildHouseEligibilityCheck(Property pr, int i, int pos) {
        if (pr.getNoOfHouse() == 0) {
            int w = 0;
            String colorSet = pr.getColor();
            int ownerID = pr.getOwnerID();

            for (int j = 0; j < properties.length; j++) {
                if (properties[j].getColor().equals(colorSet)) {
                    if (properties[j].getOwnerID() != ownerID) {
                        w = 100;
                    }
                }
            }
            if (w == 0)
                return true;
            else
                return false;
        } else if (pr.getNoOfHouse() < 4) {
            return true;
        } else
            return false;

    }

    public boolean buildHotelEligibilityCheck(Property pr, int i, int pos) {
        if (pr.getNoOfHouse() == 4)
            return true;
        return false;
    }

    public void buildHouse(Property pr, int i, int pos) {
        payToBank(pr.getHousePrice(), i, true);
        dm("takeFine#" + String.valueOf(pr.getHousePrice()), i);
        pr.setNoOfHouse(pr.getNoOfHouse() + 1);
    }

    public void buildHotel(Property pr, int i, int pos) {
        payToBank(pr.getHotelPrice(), i, true);
        dm("takeFine#" + String.valueOf(pr.getHotelPrice()), i);
        players[i].setAsset(players[i].getAsset() - pr.getHousePrice() * 4);
        pr.setNoOfHotel(1);
        pr.setNoOfHouse(100);
    }

    public void run() {
        initializeTheBoard();
        assignBoardPosition();
        setPlayers();

        for (int j = 0; j < numPlayer; j++) {
            System.out.println("Player no " + playerIDwholelist[j]);
        }
        while (true) {
            System.out.println("This is match " + matchID);

            for (int i = 0; i < numPlayer; i++) {
                if (players[i].isInJail() == true) {
                    System.out.println("Player no " + (i + 1) + "'s turn");
                    dm("You are in jail. pay fee.", i);
                    String s = readClientsMsg(i);
                    if (s.equals("ok pay")) {
                        payToBank(50, i, false);
                        dm("takeFine#50", i);
                        players[i].setInJail(false);
                        dm("you are free !!", i);
                        String ss = readClientsMsg(i);
                        if (ss.equals("yes im free"))
                            bc("Other enable");
                    }

                }

                String msg = "";
                System.out.println("filemsg.equals(null) before the loop: " + msg);

                msg = readClientsMsg(i);

                System.out.println(i + " filemsg: " + msg);
                if (msg.startsWith("rolldice")) {

                    String newmsg[] = msg.split("#");

                    int dice1 = Integer.parseInt(newmsg[1]);
                    int dice2 = Integer.parseInt(newmsg[2]);

                    bc(String.valueOf(i + 1) + "#confirm#" + String.valueOf(dice1) + "#" + String.valueOf(dice2));

                    StartCheck(i, dice1 + dice2);

                    players[i].setPosition((players[i].getPosition() + dice1 + dice2) % 40);
                    int pos = players[i].getPosition();

                    if (pos == 2 || pos == 17 || pos == 33) {
                        int x = Fortune(i);
                        if (x == 1 || x == 6 || x == 7 || x == 11 || x == 12)
                            bc("Other enable");
                    }

                    pos = players[i].getPosition();

                    if (pos == 22 || pos == 7 || pos == 36) {
                        int x = Opportunity(i);
                        if (x != 12)
                            bc("Other enable");
                    }

                    pos = players[i].getPosition();

                    if (pos == 0 || pos == 10 || pos == 20) {
                        bc("Other enable");
                    }
                    if (pos == 30) {
                        goToJail(i);
                        bc(String.valueOf(i + 1) + "jail immediately");
                        String s = readClientsMsg(i);

                        if (s.equals("ok gelam :(")) {
                            System.out.println("You're in jail...Other enable ");
                            bc("Other enable");
                        }
                        bc("Other enable");
                    }

                    if (pos == 4) {
                        dm("paytax1", i);
                        String s = readClientsMsg(i);

                        if (s.equals("ok pay")) {
                            payToBank(200, i, false); // tax
                            dm("takeFine#200", i);
                        }
                        bc("Other enable");
                    }
                    if (pos == 38) {
                        dm("paytax2", i);
                        String s = readClientsMsg(i);

                        if (s.equals("ok pay")) {
                            payToBank(100, i, false); // tax
                            dm("takeFine#100", i);
                        }
                        bc("Other enable");
                    }

                    if (pos % 5 == 0 && pos % 10 != 0) {
                        String strName = board[pos];
                        int idx = arraySearch(strName, stations);

                        if (stations[idx].getOwnerID() == (i + 1)) {

                        }

                        else if (stations[idx].isBought() == true) {
                            dm("pay rent", i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok pay")) {
                                payRent(stations[idx], i);
                            }
                        } else {
                            System.out
                                    .println("I will buy this property " + String.valueOf(stations[idx].getRecordID()));
                            dm("buy this property" + String.valueOf(stations[idx].getRecordID()), i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok buy")) {
                                buy(stations[idx], i, pos);
                                Station.rentControl(stations, i);
                                System.out.println("rent crntrl done");

                            }
                        }
                        bc("Other enable");
                    }

                    else if (pos == 12 || pos == 28) {
                        String strName = board[pos];
                        int idx = arraySearch(strName, utilities);

                        if (utilities[idx].getOwnerID() == (i + 1)) {

                        }

                        else if (utilities[idx].isBought() == true) {
                            dm("pay rent", i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok pay")) {
                                payRent(utilities[idx], i, dice1 + dice2);
                            }
                        } else {
                            System.out.println(
                                    "I will buy this property " + String.valueOf(utilities[idx].getRecordID()));

                            dm("buy this property" + String.valueOf(utilities[idx].getRecordID()), i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok buy")) {

                                buy(utilities[idx], i, pos);
                                Utility.rentControl(utilities, i);
                            }
                        }
                        bc("Other enable");

                    }

                    else if (pos == 1 || pos == 3 || pos == 6 || pos == 8 || pos == 9 || pos == 11 || pos == 13
                            || pos == 14 || pos == 16 || pos == 18 || pos == 19 || pos == 21 || pos == 23 || pos == 24
                            || pos == 26 || pos == 27 || pos == 29 || pos == 31 || pos == 32 || pos == 34 || pos == 37
                            || pos == 39) {
                        String strName = board[pos];
                        int idx = arraySearch(strName, properties);
                        System.out.println("The house will be able to settle down "
                                + buildHouseEligibilityCheck(properties[idx], i, pos));

                        if (properties[idx].getOwnerID() == (i + 1)) {
                            if (buildHouseEligibilityCheck(properties[idx], i, pos)) {
                                dm("Build house?", i);
                                String s = readClientsMsg(i);

                                if (s.equals("hmm boshabo")) {
                                    buildHouse(properties[idx], i, pos);
                                }

                                else {

                                }
                            }

                            else if (buildHotelEligibilityCheck(properties[idx], i, pos)) {
                                dm("Build hotel?", i);
                                String s = readClientsMsg(i);

                                if (s.equals("hmm boshabo")) {
                                    buildHouse(properties[idx], i, pos);
                                }

                                else {

                                }
                            } else {
                                dm("Nothing", i);
                                String s = readClientsMsg(i);

                            }

                        }

                        else if (properties[idx].isBought() == true) {
                            dm("pay rent", i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok pay")) {
                                payRent(properties[idx], i, true);
                            }

                        } else {
                            System.out.println(
                                    "I will " + "buy this property" + String.valueOf(properties[idx].getRecordID()));
                            dm("buy this property" + String.valueOf(properties[idx].getRecordID()), i);
                            String s = readClientsMsg(i);

                            if (s.equals("ok buy")) {
                                buy(properties[idx], i, pos);
                            } else {

                            }

                        }
                        bc("Other enable");
                    }
                    System.out.println("dice rolls are..." + (dice1 + dice2));

                    {
                        System.out.println(players[i].getName() + " is at " + players[i].getPosition());
                        System.out.println("property:");
                        for (int x = 0; x < players[i].getRecords().size(); x++)
                            System.out.println(players[i].getRecords().elementAt(x) + " at "
                                    + board[players[i].getRecords().elementAt(x)]);
                        System.out.println("cash " + players[i].getCash() + " money " + players[i].getAsset());
                        System.out.printf("\n");
                    }
                }

                for (int ii = 0; ii < numPlayer; ii++) {
                    if (players[ii].getCash() <= 0) {
                        int idx = ii;
                        int max = 0;
                        int maxidx = -1;
                        for (int j = 0; j < numPlayer; j++) {
                            if (idx == j)
                                continue;
                            else {
                                if (players[j].getAsset() > max)
                                    max = players[j].getAsset();
                                maxidx = j;
                            }
                        }

                        bc("Winner player" + String.valueOf(maxidx + 1));

                    }
                }
            }
        }
    }

    public void bc(String bmsg) {
        for (int a = 0; a < numPlayer; a++) {
            GameServer.pwList.elementAt(playerIDwholelist[a] - 1).getOutToClient().println(bmsg);
            GameServer.pwList.elementAt(playerIDwholelist[a] - 1).getOutToClient().flush();
        }
    }

    public void dm(String dmsg, int i) {
        GameServer.pwList.elementAt(playerIDwholelist[i] - 1).getOutToClient().println(dmsg);
        GameServer.pwList.elementAt(playerIDwholelist[i] - 1).getOutToClient().flush();

    }

    public String readClientsMsg(int i) {
        String filemsg = "";
        try {
            filemsg = GameServer.pwList.elementAt(playerIDwholelist[i] - 1).getInFromClient().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filemsg;
    }
}