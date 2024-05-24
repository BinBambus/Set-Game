package org.example.set_game;

public class Cards {
    // 1234
    // 4 = SHAPE
    // 3 = FARBE
    // 2 = filling
    // 1 = Anzahl
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
    /*
    public static void main(String[] args) {
        if ((shape == shape || shape != shape)&& (colour == colour || colour != colour)&& (filling == filling || filling != filling)&& (count == count || count != count)) {
            System.out.println("Its a SET");
        }else {
            System.out.println("Its NOT a SET");
        }
    }*/
}
