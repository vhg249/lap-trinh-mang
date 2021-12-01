package com.Server;

public class Utility extends Record {
    private static int factor = 4;
    public Utility(String name, int recordID, int ownerID, int price, int rent, boolean isBought, boolean isMortgaged) {
        super(name, recordID, ownerID, price, rent, isBought, isMortgaged);
    }

    public static int getFactor() {
        return factor;
    }

    public static void setFactor(int factor) {
        Utility.factor = factor;
    }

    public static void rentControl(Utility utilities[], int i)
    {
        //if(super.name)
        if(utilities[0].getOwnerID() == utilities[1].getOwnerID())
        {
            Utility.setFactor(10);
        }
    }
}
