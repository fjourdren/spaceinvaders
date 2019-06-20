package fr.space.Classes;

import fr.space.Models.GameModel;

public class Bullet extends Entity {
    private GameModel gameModel;

    public Bullet(GameModel gameModel, Position position, int life, Sprite sprite) {
        super(position, life, sprite);

        this.setGameModel(gameModel);
    }


    public void update(double delta) {
        this.move(delta, 0, -1);

        int pixelLimitToDestroyObject =  0 - this.getSprite().getyDimension();
        if(this.getPosition().getY() < pixelLimitToDestroyObject) {
            this.destroy();
        }
    }


    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
