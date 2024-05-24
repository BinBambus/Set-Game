package org.example.set_game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayerWindows extends Application {

    private int player = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage playerStage) {

        //Setting Root
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        //Scene
        Scene scene = new Scene(vbox,250,250, Color.WHITE);

        //Buttons and Labels

        // Nur fürs Beispiel
        int playerScore = 0;
        Label scoreLabel = new Label("Score: " +playerScore);


        // Nur fürs Beispiel
        int playerTimer = 15;
        Label timeLeftLabel = new Label("Time Left: "+playerTimer);


        Button playerSetButton = new Button("SET");


        //SetStage
        playerStage.setTitle("Player "+player);
        playerStage.setScene(scene);
        playerStage.show();

        //Adding Button and TexField to Scence
        vbox.getChildren().addAll(scoreLabel,timeLeftLabel,playerSetButton);
    }
    public void setPlayer(int player){
        this.player = player;
    }
}
