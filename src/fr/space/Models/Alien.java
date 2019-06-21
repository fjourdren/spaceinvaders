package fr.space.Models;

public class Alien extends Entity {

    private static float speed = 1.0f;
    private int score = 1; // score que vaut l'alien à sa mort


    /*
     * Constructors
     * */
    public Alien(Alien alien) {
        super(alien.getPosition(), alien.getLife(), alien.getSprite());
        
        this.setScore(alien.getScore());
    }

    public Alien(Position position, int life, Sprite sprite) {
        super(position, life, sprite);
    }



    /*
     * Methods
     * */
    public void update(double delta) {

    }



    /*
     * GETTER & SETTER
     * */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
