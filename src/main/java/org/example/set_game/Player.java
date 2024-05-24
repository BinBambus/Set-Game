package org.example.set_game;

public class Player {

    private String name;
    private int score;
    private int timer;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
    //SET AND GET NAME
    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }
    //SET AND GET SCORE
    public int getScore() {return score;}

    public void setScore(int score) {
        this.score = score;

    }
    public void addScore (){
        score ++;
    }
    //Timer





}
