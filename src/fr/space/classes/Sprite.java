package fr.space.classes;

import java.awt.image.BufferedImage;

public class Sprite {
    private static Sprite spriteBullet = new Sprite(RessourceLoader.loadBufferedImageFromRessources("shot.gif"));
    private static Sprite spriteAlien = new Sprite(RessourceLoader.loadBufferedImageFromRessources("alien.gif"));
    private static Sprite spriteShip = new Sprite(RessourceLoader.loadBufferedImageFromRessources("ship.gif"));

    private static Sprite spriteExplosion = new Sprite(RessourceLoader.loadBufferedImageFromRessources("explosion.png"));

    private static Sprite background = new Sprite(RessourceLoader.loadBufferedImageFromRessources("background.jpg"));

    private BufferedImage image;
    private int xDimension, yDimension;


    public Sprite(BufferedImage image) {
        this.setImage(image);
    }


    public void calculateSize() {
        this.setxDimension(this.image.getWidth());
        this.setyDimension(this.image.getHeight());
    }


    public static Sprite getSpriteBullet() {
        return spriteBullet;
    }

    public static void setSpriteBullet(Sprite spriteBullet) {
        Sprite.spriteBullet = spriteBullet;
    }

    public static Sprite getSpriteAlien() {
        return spriteAlien;
    }

    public static void setSpriteAlien(Sprite spriteAlien) {
        Sprite.spriteAlien = spriteAlien;
    }

    public static Sprite getSpriteShip() {
        return spriteShip;
    }

    public static void setSpriteShip(Sprite spriteShip) {
        Sprite.spriteShip = spriteShip;
    }

    public static Sprite getSpriteExplosion() {
        return spriteExplosion;
    }

    public static void setSpriteExplosion(Sprite spriteExplosion) {
        Sprite.spriteExplosion = spriteExplosion;
    }

    public static Sprite getBackground() {
        return background;
    }

    public static void setBackground(Sprite background) {
        Sprite.background = background;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.calculateSize();
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
}
