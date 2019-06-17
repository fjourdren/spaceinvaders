package fr.space.classes;

public class Alien extends Entity {

    private static float speed = 1.0f;
    private int score = 1;

    public Alien(Alien alien) {
        super(alien.getPosition(), alien.getLife(), alien.getSprite());
        
        this.setScore(alien.getScore());
    }

    public Alien(Position position, int life, Sprite sprite) {
        super(position, life, sprite);
    }


    public void update(double delta) {

    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
