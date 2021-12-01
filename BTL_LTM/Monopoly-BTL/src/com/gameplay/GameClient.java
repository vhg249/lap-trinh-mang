package com.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class GameClient {

    Socket s = null;
    private BufferedReader inFromServer = null;
    private PrintWriter outToServer = null;
    private int matchPlayers;
    private Vector<String> squareName = new Vector<>();
    private Vector<Integer> mySquares = new Vector<>();
    int serial ;
    String username;

    public String getUsername() {
        return username;
    }

    public Socket getS() { return s; }

    public void setS(Socket s) { this.s = s; }

    public void setUsername(String username) {
        this.username = username;
    }

    public BufferedReader getInFromServer() {
        return inFromServer;
    }

    public PrintWriter getOutToServer() {
        return outToServer;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getSerial() {
        return serial;
    }

    public int getMatchPlayers() {
        return matchPlayers;
    }

    public void setMatchPlayers(int matchPlayers) {
        this.matchPlayers = matchPlayers;
    }

    public Vector<String> getSquareName() {
        return squareName;
    }

    public Vector<Integer> getMySquares() {
        return mySquares;
    }

    public GameClient()
    {
        try {
            s = new Socket("14.232.29.216", 61689);
            System.out.println("connected");
        } catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        try {
            inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outToServer = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        squareName.add("Farmgate");
        squareName.add("Shahbag");
        squareName.add("Palashi");
        squareName.add("Azimpur");
        squareName.add("Dhanmondi");
        squareName.add("Tongi");
        squareName.add("Khilkhet");
        squareName.add("Shyamoli");
        squareName.add("Pallabi");
        squareName.add("Mirpur");
        squareName.add("Uttara");
        squareName.add("Motijheel");
        squareName.add("Arambagh");
        squareName.add("Wari");
        squareName.add("Jatrabari");
        squareName.add("Khilgaon");
        squareName.add("Shantinagar");
        squareName.add("Baridhara");
        squareName.add("Kuril");
        squareName.add("Badda");
        squareName.add("Gulshan");
        squareName.add("Banani");
        squareName.add("Tejgaon Station");
        squareName.add("Airport Station");
        squareName.add("Kamalapur Station");
        squareName.add("Cantonment Station");
        squareName.add("Electric Company");
        squareName.add("Water Works");


        for(int i=0;i<28; i++)
        {
            mySquares.add(0);
        }
    }
    
    public void closeConnect(){
        try{
            inFromServer.close();
            outToServer.close();
            s.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
