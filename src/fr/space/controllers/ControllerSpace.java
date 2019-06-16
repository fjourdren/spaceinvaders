package fr.space.controllers;

import fr.space.classes.Keyboard;
import fr.space.models.ModelSpace;
import fr.space.views.BoardPanel;

public class ControllerSpace extends Controller {

    public ControllerSpace(ModelSpace gameModel) {
        super(gameModel);
    }

    public void setBoard(BoardPanel boardPanel) {
        this.getGameModel().getGame().setBoardPanel(boardPanel);
    }

    public BoardPanel getBoardPanel() { return this.getGameModel().getGame().getBoardPanel(); }

    public void setKeyboard(Keyboard keyboard) {
        this.getGameModel().getGame().setKeyboard(keyboard);
    }

    public int getScore() {
        return this.getGameModel().getGame().getScore();
    }

}
