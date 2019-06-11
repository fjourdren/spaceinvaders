package fr.space.classes;

import java.awt.*;

public class Spaceship extends Entity {
    private Game game;

    private static float speed = 2.0f;
    private static long shootInterval = 500000000; //0.5s in nanosecond
    private static long lastShootTime;

    public Spaceship(Game game, Position position, int life, String spritePath) {
        super(position, life, spritePath);
        this.game = game;
        this.lastShootTime = System.nanoTime();
    }


    public Spaceship(Game game, Position position, int life, Sprite sprite) {
        super(position, life, sprite);
        this.game = game;
        this.lastShootTime = System.nanoTime();
    }


    public void render(Graphics g) {
        g.drawImage(this.getSprite().getImage(), this.getPosition().getX(), this.getPosition().getY(), null);
    }


    public void update(double delta) {

    }


    public void shoot() {
        long now = System.nanoTime();

        if(now - this.lastShootTime > shootInterval) {
            Sprite spriteBullet = new Sprite("/home/flavien/Dropbox/projet_space_invaders/projet_space_invaders/shot.gif");
            Position bulletPosition = new Position((this.getPosition().getX() + (this.getSprite().getxDimension() / 2)) - spriteBullet.getxDimension() / 2, this.getPosition().getY());

            this.lastShootTime = System.nanoTime();
            this.game.addBullet(new Bullet(this.game, bulletPosition, 1, spriteBullet));
        }
    }


    public void move(double delta, double xAbsice) {
        int newX = (int) (this.getPosition().getX() + xAbsice * Spaceship.getSpeed() * delta);

        if(newX >= 0 && newX <= this.getGame().getxSize() - this.getSprite().getxDimension())
            this.setPosition(new Position(newX, this.getPosition().getY()));
    }


    public void destroy() {
        this.setLife(0);
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static float getSpeed() {
        return speed;
    }

    public static void setSpeed(float speed) {
        Spaceship.speed = speed;
    }

    public static long getShootInterval() {
        return shootInterval;
    }

    public static void setShootInterval(long shootInterval) {
        Spaceship.shootInterval = shootInterval;
    }
}
