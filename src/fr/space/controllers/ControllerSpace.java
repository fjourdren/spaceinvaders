package fr.space.controllers;

import fr.space.models.ModelSpace;
import fr.space.views.BoardPanel;

public class ControllerSpace extends Controller {

    public ControllerSpace(ModelSpace gameModel) {
        super(gameModel);
    }


    public void setBoard(BoardPanel boardPanel) {
        this.getModel().getGame().setBoardPanel(boardPanel);
    }

}
