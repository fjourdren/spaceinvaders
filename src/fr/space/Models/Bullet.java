package fr.space.Models;

public class Bullet extends Entity {

    private GameModel gameModel;


    /*
     * Constructors
     * */
    public Bullet(GameModel gameModel, Position position, int life, Sprite sprite, Entity parent) {
        super(position, life, sprite);

        this.setGameModel(gameModel);
        this.setParent(parent);
    }



    /*
     * Methods
     * */
    public void update(double delta) {
        // déplacement du bullet en permanence vers le haut
        this.move(delta, 0, -1);

        // si la bullet est sortie de l'écran, on la supprime
        int pixelLimitToDestroyObject =  0 - this.getSprite().getyDimension();
        if(this.getPosition().getY() < pixelLimitToDestroyObject) {
            this.destroy();
        }
    }



    /*
     * GETTER & SETTER
     * */
    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
