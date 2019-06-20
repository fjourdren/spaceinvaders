package fr.space.Models;

public class Position extends Model {

    private int x, y;


    /*
     * Constructors
     * */
    public Position(int x, int y) {
        this.setX(x);
        this.setY(y);
    }



    /*
     * GETTER & SETTER
     * */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
