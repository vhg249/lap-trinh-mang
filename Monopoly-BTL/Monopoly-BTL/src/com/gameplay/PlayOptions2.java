package com.gameplay;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.OpeningWindow;

import java.io.*;

public class PlayOptions2 {
    static int howmanyplayers;

    @FXML
    Label no, loading;

    @FXML
    ProgressBar pb;

    @FXML
    Button two, three, four, back;

    Stage stage;

    public void takeme2MGS(ActionEvent event)throws IOException
    {
        Parent pr = FXMLLoader.load(getClass().getResource("/com/gameplay/Board.fxml"));
        Scene scene = new Scene(pr);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void twoClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 2;
        OpeningWindow.getgc().setMatchPlayers(2);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr: "+ numstr);
        OpeningWindow.getgc().getOutToServer().println(numstr);
        OpeningWindow.getgc().getOutToServer().flush();
        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }

    public void threeClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 3;
        OpeningWindow.getgc().setMatchPlayers(3);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr:  "+ numstr);
        OpeningWindow.getgc().getOutToServer().println(numstr);
        OpeningWindow.getgc().getOutToServer().flush();
        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }
    public void fourClicked(ActionEvent event) throws IOException
    {
        howmanyplayers = 4;
        OpeningWindow.getgc().setMatchPlayers(4);
        String numstr = "";

        numstr = String.valueOf(howmanyplayers);
        System.out.println("numstr:  "+ numstr);
        OpeningWindow.getgc().getOutToServer().println(numstr);
        OpeningWindow.getgc().getOutToServer().flush();

        stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        changeScene();
    }


    public void backbuttonClicked(ActionEvent event) throws IOException
    {
        Parent Start = FXMLLoader.load(getClass().getResource("/com/OpeningWindow.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }



    public void changeScene() {

        no.setVisible(false); no.setDisable(true);
        two.setVisible(false);  two.setDisable(true);
        three.setVisible(false);  three.setDisable(true);
        four.setVisible(false);  four.setDisable(true);
        back.setVisible(false);  back.setDisable(true);

        loading.setVisible(true);
        pb.setVisible(true);    pb.setDisable(false);

        Thread th = new Thread(new Task< String >() {

            @Override
            protected String call() throws Exception {

                String str = "";

                try {
                    System.out.println("Waiting");
                    str = OpeningWindow.getgc().getInFromServer().readLine();
                    System.out.println(str);

                    OpeningWindow.getgc().getOutToServer().println("Match found!");
                    OpeningWindow.getgc().getOutToServer().flush();
                } catch (Exception e)
                {
                    System.err.println(e);
                }

                Thread.sleep(1018);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameplay/Board.fxml"));
                Region region = loader.load();
                Group group = new Group(region);
                StackPane pane = new StackPane();
                pane.getChildren().add(group);
                Scene scene = new Scene(pane);
                group.scaleXProperty().bind(scene.widthProperty().divide(1280));
                group.scaleYProperty().bind(scene.heightProperty().divide(720));


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.setScene(scene);
                        stage.show();
                    }
                });

                return "Hudai";
            }
        });

        th.setDaemon(true);
        th.start();

    }

}