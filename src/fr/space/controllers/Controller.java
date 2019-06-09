package fr.space.controllers;

import fr.space.models.ModelSpace;

public class Controller {
    private ModelSpace gameModel;

    public Controller(ModelSpace gameModel) {
        this.gameModel = gameModel;
    }


    public ModelSpace getModel() {
        return this.gameModel;
    }
}
