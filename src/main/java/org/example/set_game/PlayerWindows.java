package org.example.set_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;

public class PlayerWindows extends Application {

    private int player = 1;
    private int playerTimer = 15;
    private int playerScore = 0;
    Timeline timeline;

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
        Label scoreLabel = new Label("Score: " +playerScore);


        // Nur fürs Beispiel
        Label timeLeftLabel = new Label("Time Left: "+playerTimer);




        Button playerSetButton = new Button("SET");
        playerSetButton.setOnAction(e -> {
            start1orEnd0_PlayerTimer(true, scoreLabel, timeLeftLabel);
        });


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

    //Starting the Timer after SET-Button is pressed
    public void start1orEnd0_PlayerTimer(boolean endOrStart, Label scoreLabel, Label timeLeftLabel){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(playerTimer > 0){
                playerTimer--;
                timeLeftLabel.setText("Time Left: " + playerTimer);//Update Timer
            } else{
                playerTimer = 15;
                playerScore--;
                scoreLabel.setText("Score: " + playerScore);//Update Score if time runs out (Score -1)
                timeLeftLabel.setText("Time Left: " + playerTimer);//Update Timer
                timeline.stop();
            }

        }));
        if(endOrStart){
            timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
            timeline.play(); // Start the timeline
        }else{
            timeline.stop();
            playerTimer = 15;
        }

    }

}
