package fr.space.classes;

import java.awt.*;

public class Bullet extends Entity {
    private Game game;

    public Bullet(Game game, Position position, int life, Sprite sprite) {
        super(position, life, sprite);

        this.setGame(game);
    }


    public void update(double delta) {
        this.move(delta, 0, -1);

        int pixelLimitToDestroyObject =  0 - this.getSprite().getyDimension();
        if(this.getPosition().getY() < pixelLimitToDestroyObject) {
            this.destroy();
        }
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
