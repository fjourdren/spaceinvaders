package fr.space.classes;

import java.awt.*;

public class Spaceship extends Entity {
    private static float speed = 1.0f;
    private static float shootInterval = 1.0f;

    public Spaceship(Position position, int life, String spritePath) {
        super(position, life, spritePath);
    }


    public void render(Graphics g) {
        //g.drawImage(lune, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(this.getSprite().getImage(), this.getPosition().getX(), this.getPosition().getY(), null);
    }


    public void update(double delta) {

    }
}
