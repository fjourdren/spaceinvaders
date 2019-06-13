package fr.space.classes;

import java.awt.image.BufferedImage;

public class Sprite {
    private final static Sprite spriteBullet = new Sprite(RessourceLoader.loadBufferedImage("shot.gif"));
    private final static Sprite spriteAlien = new Sprite(RessourceLoader.loadBufferedImage("alien.gif"));
    private final static Sprite spriteShip = new Sprite(RessourceLoader.loadBufferedImage("ship.gif"));

    private BufferedImage image;
    private int xDimension, yDimension;


    public Sprite(BufferedImage image) {
        this.image = image;
        this.calculateSize();
    }


    public void calculateSize() {
        this.xDimension = this.image.getWidth();
        this.yDimension = this.image.getHeight();
    }


    public BufferedImage getImage() {
        return image;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }


    public int getxDimension() {
        return xDimension;
    }


    public void setxDimension(int xDimension) {
        this.xDimension = xDimension;
    }


    public int getyDimension() {
        return yDimension;
    }

    public void setyDimension(int yDimension) {
        this.yDimension = yDimension;
    }

    public static Sprite getSpriteBullet() {
        return spriteBullet;
    }

    public static Sprite getSpriteAlien() {
        return spriteAlien;
    }

    public static Sprite getSpriteShip() {
        return spriteShip;
    }
}
