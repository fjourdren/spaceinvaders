package fr.space.controllers;

import fr.space.models.ModelSpace;

public abstract class Controller {
    private ModelSpace gameModel;

    public Controller(ModelSpace gameModel) {
        this.setGameModel(gameModel);
    }


    public ModelSpace getGameModel() {
        return gameModel;
    }

    public void setGameModel(ModelSpace gameModel) {
        this.gameModel = gameModel;
    }
}
