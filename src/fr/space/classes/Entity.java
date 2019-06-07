package fr.space.classes;

public abstract class Entity {
    private Position position;
    private int life;
    private String spritePath;

    public Entity(Position position, int life, String spritePath) {
        this.position = position;
        this.life = life;
        this.spritePath = spritePath;
    }
}
