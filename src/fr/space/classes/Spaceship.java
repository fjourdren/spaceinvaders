package fr.space.classes;

import java.awt.*;

public class Spaceship extends Entity {
    private Game game;


    private static long shootInterval = 500000000; //0.5s in nanosecond
    private static long lastShootTime;

    public Spaceship(Game game, Position position, int life, Sprite sprite) {
        super(position, life, sprite);
        this.game = game;
        this.lastShootTime = System.nanoTime();
    }


    public void update(double delta) {

    }


    public void shoot() {
        long now = System.nanoTime();

        if (now - this.lastShootTime > shootInterval) {

            Position bulletPosition = new Position((this.getPosition().getX() + (this.getSprite().getxDimension() / 2)) - Sprite.getSpriteBullet().getxDimension() / 2, this.getPosition().getY());

            this.lastShootTime = System.nanoTime();
            this.game.addBullet(new Bullet(this.game, bulletPosition, 1, Sprite.getSpriteBullet()));
        }
    }

    public void move(double delta, double xAbsice, double yAbsice) {
        int newX = (int) (this.getPosition().getX() + xAbsice * this.getSpeed() * delta);

        if(newX >= 0 && newX <= this.getGame().getxSize() - this.getSprite().getxDimension())
            super.move(delta, xAbsice, yAbsice);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static long getShootInterval() {
        return shootInterval;
    }

    public static void setShootInterval(long shootInterval) {
        Spaceship.shootInterval = shootInterval;
    }
}
