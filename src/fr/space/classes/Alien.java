package fr.space.classes;

import java.awt.*;

public class Alien extends Entity {

    private static float speed = 1.0f;

    public Alien(Position position, int life, String spritePath) {
        super(position, life, spritePath);
    }


    public Alien(Position position, int life, Sprite sprite) {
        super(position, life, sprite);
    }


    public void render(Graphics g) {

    }


    public void update(double delta) {

    }


    public void destroy() {

    }
}
