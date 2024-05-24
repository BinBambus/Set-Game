package org.example.set_game;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.ArrayList;


public class SetGameWindow extends Application {
    Scene scene1,scene2;
    private int players = 1; //Anzahl der Spieler

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage setGameStage){
        //Karten Initzialisieren
        ArrayList<Cards> cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        cards.add(new Cards(i, j, k, l));
                    }
                }
            }
        }
        //Karten mischen
        Collections.shuffle(cards);




        //Starting Screen
        //Setting Root pane
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        //Setting Scene
        scene1 = new Scene(vbox,800,800, Color.WHITE);

        //Creating Buttons and Textfield
        Image image = new Image("SetGamePicture.png");
        ImageView icon = new ImageView(image);
        Button submit = new Button("Submit");
        Button start = new Button("Start");
        //If Start Button is pressed theres an Switch to the game scene



        TextField playerCounter = new TextField();
        playerCounter.setMaxWidth(200);
        playerCounter.setPromptText("Enter Number of Players (Default: 1)");
        playerCounter.setStyle("-fx-alignment: center;");
        //Textfield nur Zahlen als eingabe
        playerCounter.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            // Erlaube nur Zahlen (0-9) und Backspace
            if (!"0123456789".contains(event.getCharacter())) {
                event.consume();
            }
        });

        //Wert verwenden aus dem Textfield
        submit.setOnAction(event -> {
            // Hole den eingegebenen Wert aus dem TextField
            String wert = playerCounter.getText();
            // Konvertiere den Wert in eine Zahl (int)
            players = Integer.parseInt(wert);
        });
        //Spieler Fenster initzialisieren und auf Spielbrett wechseln
        start.setOnAction(e->{
            setGameStage.setScene(scene2);
            for (int i = 0; i < players; i++) {
                PlayerWindows playerWindow = new PlayerWindows();
                playerWindow.setPlayer(i+1);
                playerWindow.start(new Stage());
            }
        });



        //Stage
        setGameStage.setTitle("SetGame");
        setGameStage.setScene(scene1);
        setGameStage.show();

        //Adding Button and TexField to Scence
        vbox.getChildren().addAll(icon, playerCounter,submit,start);

        //Braucht man weil sonst der Focus auf dem Textfeld liegt und man sieht das Textpromt nicht
        icon.requestFocus();



        //Game Screen
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        //grid.add(new ImageView(cards.get(0).getName()), col,row);

        // Tabelle
        int numCols = 4;
        int numRows = 3;
        int counter = 0;


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                grid.add(new ImageView(cards.get(counter).getName()+".png"), col,row);
                counter++;
            }
        }

        Button endGame = new Button("End Game");
        Button noSet = new Button("No Set");

        HBox bottomButtons = new HBox();
        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
        bottomButtons.setSpacing(125);
        bottomButtons.getChildren().addAll(noSet, endGame);







        VBox layout = new VBox(50);
        layout.setPadding(new Insets(20,20,10,20));

        layout.getChildren().addAll(grid,bottomButtons);
        //Scene
        scene2 = new Scene(layout,800,800);


        //Setstage
        setGameStage.setTitle("SetGame");
        setGameStage.setScene(scene1);
        setGameStage.show();

        //Öffnen Spieler Fenster
        for (int i = 0; i < players; i++) {}
    }
}
