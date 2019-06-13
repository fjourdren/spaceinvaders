package fr.space.classes;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private Position position;
    private int life;
    private float speed = 2.0f;
    private Sprite sprite;


    public Entity(Position position, int life, Sprite sprite) {
        this.position = position;
        this.life = life;
        this.sprite = sprite;
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
        g.drawImage(this.getSprite().getImage(), this.getPosition().getX(), this.getPosition().getY(), null);
    }


    public void update(double delta) {

    }

    public void move(double delta, double xAbsice, double yAbsice) {
        int newX = (int) (this.getPosition().getX() + xAbsice * this.getSpeed() * delta);
        int newY = (int) (this.getPosition().getY() + yAbsice * this.getSpeed() * delta);


        this.setPosition(new Position(newX, newY));
    }


    public void destroy() {
        this.setLife(0);
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
