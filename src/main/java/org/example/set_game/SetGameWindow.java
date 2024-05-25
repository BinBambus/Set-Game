package org.example.set_game;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private int players = 1;
    int gridUpdateCounter = 0;
    PlayerWindows[] playerWindows;//Anzahl der Spieler
    GridPane grid;
    HBox bottomButtons;
    public static int kartenAngeklickt = 0;
    private int karte1;
    private int karte2;
    private int karte3;
    public static boolean setKlicked = false;
    public static int playerwhopressedSet;

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
            //Spieler im Array sammeln
            playerWindows = new PlayerWindows[players];
            setGameStage.setScene(scene2);
            //Spieler Fenster intialisieren
            for (int i= 0; i < players; i++){
                playerWindows[i] = new PlayerWindows();
                playerWindows[i].setPlayer(i+1);
                playerWindows[i].start(new Stage());
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
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        //GridPane
        buildGridPane(cards);

        Button endGame = new Button("End Game");
        Button noSet = new Button("No Set");
        Button refresh = new Button("Refresh");
        refresh.setOnAction(e->{
            if (buildGridPane(cards)!= null){
                setGameStage.setScene(buildGridPane(cards));
            }

        });

        bottomButtons = new HBox();
        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
        bottomButtons.setSpacing(125);
        bottomButtons.getChildren().addAll(noSet,refresh, endGame);






        //Layout für Gridpane und Buttons
        VBox layout2 = new VBox(50);
        layout2.setPadding(new Insets(20,20,10,20));

        layout2.getChildren().addAll(grid,bottomButtons);
        //Scene
        scene2 = new Scene(layout2,800,800);


        //Setstage
        setGameStage.setTitle("SetGame");
        setGameStage.setScene(scene1);
        setGameStage.show();

        //Öffnen Spieler Fenster
        for (int i = 0; i < players; i++) {}
    }
    public boolean überprüfenSet(ArrayList<Cards> cards, int karte1, int karte2, int karte3){
        boolean isSet;
        if ((cards.get(karte1).getShape() == cards.get(karte2).getShape()) && (cards.get(karte2).getShape() == cards.get(karte3).getShape())) {
            isSet = true;
        } else if ((cards.get(karte1).getColour() == cards.get(karte2).getColour()) && (cards.get(karte2).getColour() == cards.get(karte3).getColour())){
            isSet = true;
        } else if ((cards.get(karte1).getCount() == cards.get(karte2).getCount()) && (cards.get(karte2).getCount() == cards.get(karte3).getCount())){
            isSet = true;
        } else if ((cards.get(karte1).getFilling() == cards.get(karte2).getFilling()) && (cards.get(karte2).getFilling() == cards.get(karte3).getFilling())){
            isSet = true;
        } else if ((cards.get(karte1).getShape() != cards.get(karte2).getShape()) && (cards.get(karte1).getShape() != cards.get(karte3).getShape())
                && (cards.get(karte2).getShape() != cards.get(karte3).getShape())) {
            isSet = true;
        } else if ((cards.get(karte1).getColour() != cards.get(karte2).getColour()) && (cards.get(karte1).getColour() != cards.get(karte3).getColour())
                && (cards.get(karte2).getColour() != cards.get(karte3).getColour())) {
            isSet = true;
        } else if ((cards.get(karte1).getCount() != cards.get(karte2).getCount()) && (cards.get(karte1).getCount() != cards.get(karte3).getCount()) /**/
                && (cards.get(karte2).getCount() != cards.get(karte3).getCount())) {
            isSet = true;
        } else if ((cards.get(karte1).getFilling() != cards.get(karte2).getFilling()) && (cards.get(karte1).getFilling() != cards.get(karte3).getFilling())
                && (cards.get(karte2).getFilling() != cards.get(karte3).getFilling())) {
            isSet = true;
        } else {
            isSet = false;
        }
        return isSet;
    }
    public Scene buildGridPane(ArrayList<Cards> cards){

        //grid.add(new ImageView(cards.get(0).getName()), col,row);

        // Tabelle
        int numCols = 4;
        int numRows = 3;
        int counter = 0;

        grid.getChildren().clear();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (counter >= cards.size()) {
                    break; // Beende die Schleife, wenn alle Karten hinzugefügt wurden
                }

                int index = row * numCols + col; // Berechnung des Index basierend auf row und col
                ImageView imageView = new ImageView(cards.get(counter).getName()+".png");
                grid.add(imageView, col,row);
                counter++;

                final int karteIndex = index; // Variable, um den Index innerhalb des EventListeners zu halten
                imageView.setOnMouseClicked(event -> {
                    if (setKlicked){ // Anklicken nur möglich wenn Set klicked ausgewählt ist
                        if (kartenAngeklickt == 0) {
                            karte1 = karteIndex;
                        } if (kartenAngeklickt == 1) {
                            karte2 = karteIndex;
                        }if (kartenAngeklickt == 2) {
                            karte3 = karteIndex;

                            //überprüfenSet;
                            if (überprüfenSet(cards,karte1,karte2,karte3)){
                                //Timer stoppen im Spieler Fenster, welches SET gedrückt hat
                                System.out.println("True");
                                playerWindows[playerwhopressedSet].start1orEnd0_PlayerTimer(false,playerWindows[playerwhopressedSet].getScoreLabel(),playerWindows[playerwhopressedSet].getTimeLeftLabel());
                                //Spieler score um 1 erhöhen, bei dem der SET gedrückt hat
                                playerWindows[playerwhopressedSet].setPlayerScorePlus1();

                                //Karten löschen mit dem größten index zu erst
                                Integer[] karten = {karte1, karte2, karte3};
                                Arrays.sort(karten, Collections.reverseOrder());
                                System.out.println(cards.get(karte1).getName());
                                cards.remove(karte1);
                                System.out.println(cards.get(karte2).getName());
                                cards.remove(karte2);
                                System.out.println(cards.get(karte3).getName());
                                cards.remove(karte3);
                            } else {
                                System.out.println("False");
                                //Timer stoppen im Spieler Fenster, welches SET gedrückt hat
                                playerWindows[playerwhopressedSet].start1orEnd0_PlayerTimer(false,playerWindows[playerwhopressedSet].getScoreLabel(),playerWindows[playerwhopressedSet].getTimeLeftLabel());
                                //Spieler score um 1 verringern, bei dem der SET gedrückt hat
                                playerWindows[playerwhopressedSet].setPlayerScoreMinus1();
                            }
                            kartenAngeklickt = 0;
                            setKlicked = false; // Makes every SET-Button pressable again
                        }
                        kartenAngeklickt++;
                        System.out.println("Kartenindex: " + karteIndex); // Zur Überprüfung des Index
                    }


                });
            }
        }
        if (gridUpdateCounter > 0){
            VBox layout = new VBox(50);
            layout.getChildren().addAll(grid,bottomButtons);
            layout.setPadding(new Insets(20,20,10,20));
            Scene scene = new Scene(layout,800,800);
            return scene;

        }else{
            gridUpdateCounter++;
            System.out.println(gridUpdateCounter);
            return null;
        }


    }

}
