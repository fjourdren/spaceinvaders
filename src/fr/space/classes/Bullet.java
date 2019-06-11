package fr.space.classes;

import java.awt.*;

public class Bullet extends Entity {
    private static float speed = 1.0f;
    private Game game;

    public Bullet(Game game, Position position, int life, String spritePath) {
        super(position, life, spritePath);
        this.game = game;
    }

    public Bullet(Game game, Position position, int life, Sprite sprite) {
        super(position, life, sprite);
        this.game = game;
    }


    public void render(Graphics g) {
        g.drawImage(this.getSprite().getImage(), this.getPosition().getX(), this.getPosition().getY(), null);
    }


    public void update(double delta) {
        this.getPosition().setY((int) (this.getPosition().getY() - Bullet.speed * delta));

        int pixelLimitToDestroyObject =  0 - this.getSprite().getyDimension();
        if(this.getPosition().getY() < pixelLimitToDestroyObject) {
            this.destroy();
        }
    }


    public void destroy() {
        this.setLife(0);
    }
}
