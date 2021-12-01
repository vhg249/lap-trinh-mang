package com.Server;

public class Record
{
     private String name;
     private int recordID, ownerID , price, rent;
     private boolean isBought, isMortgaged ;

    public Record() {
    }

    public Record(String name, int recordID, int ownerID, int price, int rent, boolean isBought, boolean isMortgaged) {
        this.name = name;
        this.recordID = recordID;
        this.ownerID = ownerID;
        this.price = price;
        this.rent = rent;
        this.isBought = isBought;
        this.isMortgaged = isMortgaged;
    }

    public String getName() {
        return name;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public boolean isBought() {
        return isBought;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }
}