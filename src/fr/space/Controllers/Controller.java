package fr.space.Controllers;

import fr.space.Models.GameModel;

public abstract class Controller {
    private GameModel gameModel;

    public Controller(GameModel gameModel) {
        this.setGameModel(gameModel);
    }


    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
