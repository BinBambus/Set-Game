package org.example.set_game;

public class Cards {

    private int shape;
    private int colour;
    private int filling;
    private int count;
    private String name = "s";
    public Cards() {}
    public Cards(int shape, int colour, int filling, int count) {
        this.shape = shape;
        this.colour = colour;
        this.filling = filling;
        this.count = count;
        name = name + toString(count);
        name = name + toString(filling);
        name = name + toString(colour);
        name = name + toString(shape);
    }

    private String toString(int count) {
        String str = "";
        str = str + count;
        return str;
    }

    public String getName() {
        return name;
    }
    public int getCount() {
        return count;
    }
    public int getFilling() {
        return filling;
    }
    public int getColour() {
        return colour;
    }
    public int getShape() {
        return shape;
    }
}
