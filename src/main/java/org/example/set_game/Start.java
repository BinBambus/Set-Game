/*
package org.example.set_game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Start extends Application {


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage startStage) throws Exception {
        //Setting Root
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        //Setting Scene
        Scene scene = new Scene(vbox,500,500, Color.WHITE);

        //Creating Buttons and Textfield
        Image image = new Image("SetGamePicture.png");
        ImageView icon = new ImageView(image);

        Button submit = new Button("Submit");
        Button start = new Button("Start");


        TextField playerCounter = new TextField();
        playerCounter.setMaxWidth(200);
        playerCounter.setPromptText("Enter Number of org.example.set_game.Player");
        playerCounter.setStyle("-fx-alignment: center;");



        //Stage
        startStage.setTitle("SetGame");
        startStage.setScene(scene);
        startStage.show();

        //Adding Button and TexField to Scence
        vbox.getChildren().addAll(icon, playerCounter,submit,start);

        //Braucht man weil sonst der Focus auf dem Textfeld liegt und man sieht das Textpromt nicht
        icon.requestFocus();

    }
}
*/