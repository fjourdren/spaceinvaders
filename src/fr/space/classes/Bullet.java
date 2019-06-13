package fr.space.classes;

import java.awt.*;

public class Bullet extends Entity {
    private Game game;

    public Bullet(Game game, Position position, int life, Sprite sprite) {
        super(position, life, sprite);
        this.game = game;
    }


    public void update(double delta) {
        this.getPosition().setY((int) (this.getPosition().getY() - this.getSpeed() * delta));

        int pixelLimitToDestroyObject =  0 - this.getSprite().getyDimension();
        if(this.getPosition().getY() < pixelLimitToDestroyObject) {
            this.destroy();
        }
    }
}
