package fr.space.classes;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private Position position;
    private int life;
    private Sprite sprite;


    public Entity(Position position, int life, Sprite sprite) {
        this.position = position;
        this.life = life;
        this.sprite = sprite;
    }


    public Entity(Position position, int life, String spritePath) {
        this.position = position;
        this.life = life;
        this.sprite = new Sprite(spritePath);
    }


    public Entity(Position position, int life, BufferedImage spriteImage) {
        this.position = position;
        this.life = life;
        this.sprite = new Sprite(spriteImage);
    }


    public Entity(Position position, int life) {
        this.position = position;
        this.life = life;
    }


    public boolean colisionWith(Entity e) {
        int minxImageA = this.getPosition().getX();
        int maxxImageA = this.getPosition().getX() + this.getSprite().getxDimension();

        int minyImageA = this.getPosition().getY();
        int maxyImageA = this.getPosition().getY() + this.getSprite().getyDimension();


        int minxImageB = e.getPosition().getX();
        int maxxImageB = e.getPosition().getX() + e.getSprite().getxDimension();

        int minyImageB = e.getPosition().getY();
        int maxyImageB = e.getPosition().getY() + e.getSprite().getyDimension();


        return (minxImageA < maxxImageB && maxxImageA > minxImageB &&
                minyImageA < maxyImageB && maxyImageA > minyImageB);
    }


    public void render(Graphics g) {

    }


    public void update(double delta) {

    }


    public Position getPosition() {
        return position;
    }


    public void setPosition(Position position) {
        this.position = position;
    }


    public int getLife() {
        return life;
    }


    public void setLife(int life) {
        this.life = life;
    }


    public Sprite getSprite() {
        return sprite;
    }


    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
