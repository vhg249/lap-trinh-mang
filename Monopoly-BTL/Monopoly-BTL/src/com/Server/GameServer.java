package com.Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class GameServer {
    ServerSocket welcomeSocket = null ;
    Socket connectionSocket = null;
    static int plID;
    static Vector<PlayerWorld> pwList;
    static Vector<MonopolyWorld> mwList;
    static  File file;

    static int ctwoPlayers , cthreePlayers , cfourPlayers,  qtwoPlayers , qthreePlayers , qfourPlayers;

    public static File getFile() {
        return file;
    }

    public GameServer() throws IOException, SQLException, ClassNotFoundException {
        ctwoPlayers =0; cthreePlayers = 0; cfourPlayers = 0;
        qtwoPlayers =0; qthreePlayers = 0; qfourPlayers = 0;
        plID = 1;
        pwList = new Vector<>();
        mwList = new Vector<>();
         file  = new File("serverfile.txt");
        System.out.println("Waiting players...");
        try {
            welcomeSocket = new ServerSocket(61689);
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }

        while (true )
        {
            try
            {
                connectionSocket = welcomeSocket.accept();
                System.out.printf("Player %d has joined\n",plID);
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
            }

            ctwoPlayers = PlayerWorld.getCp2();
            cthreePlayers = PlayerWorld.getCp3();
            cfourPlayers = PlayerWorld.getCp4();

            qtwoPlayers = PlayerWorld.getQp2();
            qthreePlayers = PlayerWorld.getQp3();
            qfourPlayers = PlayerWorld.getQp4();

            PlayerWorld pw = new PlayerWorld(connectionSocket, plID, ctwoPlayers, cthreePlayers, cfourPlayers, qtwoPlayers, qthreePlayers, qfourPlayers );

            pwList.add(pw);
            Thread t = new Thread(pw);
            t.start();

            plID++;
        }

    }

    public static void main(String argv[]) throws IOException, SQLException, ClassNotFoundException {
        new GameServer();
    }
}
