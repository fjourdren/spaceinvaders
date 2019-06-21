package fr.space.Models;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends Model {

    private Position position;
    private int life;
    private float speed = 2.0f;
    private Sprite sprite;
    private boolean hitbox = true;
    private Entity parent;


    /*
     * Constructors
     * */
    public Entity(Position position, int life, Sprite sprite) {
        this.setPosition(position);
        this.setLife(life);
        this.setSprite(sprite);
    }

    public Entity(Position position, int life, BufferedImage spriteImage) {
        this.setPosition(position);
        this.setLife(life);
        this.setSprite(new Sprite(spriteImage));
    }

    public Entity(Position position, int life) {
        this.setPosition(position);
        this.setLife(life);
    }



    /*
     * Methods
     * */
    // détection des collision avec une autre entité
    public boolean collisionWith(Entity e) {
        // vérifie que les deux entités doivent avoir des collisions entre elles
        if(e == this || e.getParent() == this || this.getParent() == e) {
            return false;
        }

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

    // déplacement d'une entité dans l'espace
    public void move(double delta, double xAbsice, double yAbsice) {
        int deltaInt = (int) delta;

        if(deltaInt == 0) {
            deltaInt = 1;
        }

        // calcul de la nouvelle position avec le vecteur de déplacement, la vitesse et le delta du jeu
        int newX = (int) (this.getPosition().getX() + xAbsice * this.getSpeed() * deltaInt);
        int newY = (int) (this.getPosition().getY() + yAbsice * this.getSpeed() * deltaInt);


        this.setPosition(new Position(newX, newY));
    }

    public void destroy() {
        this.setLife(0);
    }

    public void addLife(int life) {
        this.setLife(this.getLife() + life);
    }

    public void removeLife(int life) {
        this.setLife(this.getLife() - life);
    }



    /*
     * GETTER & SETTER
     * */
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

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public boolean isHitbox() {
        return hitbox;
    }

    public void setHitbox(boolean hitbox) {
        this.hitbox = hitbox;
    }
}
