package fr.space.Models;

import fr.space.Utils.RessourceLoader;

import java.awt.image.BufferedImage;

public class Sprite extends Model {

    private static Sprite spriteBullet = new Sprite(RessourceLoader.loadBufferedImageFromRessources("shot.png"));
    private static Sprite spriteAlien = new Sprite(RessourceLoader.loadBufferedImageFromRessources("alien.png"));
    private static Sprite spriteShip = new Sprite(RessourceLoader.loadBufferedImageFromRessources("ship.png"));

    private static Sprite spriteExplosion = new Sprite(RessourceLoader.loadBufferedImageFromRessources("explosion.png"));

    private static Sprite background = new Sprite(RessourceLoader.loadBufferedImageFromRessources("background.jpg"));

    private BufferedImage image;
    private int xDimension, yDimension;


    /*
     * Constructors
     * */
    public Sprite(BufferedImage image) {
        this.setImage(image);
    }



    /*
     * Methods
     * */
    // calcul de la largeur et de la hauteur du sprite
    public void calculateSize() {
        this.setxDimension(this.getImage().getWidth());
        this.setyDimension(this.getImage().getHeight());
    }



    /*
     * GETTER & SETTER with calcul
     * */
    // on force à récupérer la largeur et la hauteur de l'image dans le sprite
    public void setImage(BufferedImage image) {
        this.image = image;
        this.calculateSize();
    }



    /*
     * GETTER & SETTER
     * */
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
