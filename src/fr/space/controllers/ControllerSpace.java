package fr.space.controllers;

import fr.space.classes.Alien;
import fr.space.classes.Keyboard;
import fr.space.models.ModelSpace;
import fr.space.views.BoardPanel;

import java.util.List;

public class ControllerSpace extends Controller {

    public ControllerSpace(ModelSpace gameModel) {
        super(gameModel);
    }

    public void applyConfigIntervalShoot(long interval) {
        this.getGameModel().getGame().getPlayer();
    }


    public void applyConfigNumber(float number) {
        List<Alien> aliens = this.getGameModel().getGame().getWave().getAliens();

        if(number < aliens.size()) {
            int val = 0;
            for (Alien a: aliens) {
                ++val;

                if(val > number) {
                    a.setLife(0);
                }
            }
        }

    }


    public void applyConfigSpeed(float speed) {
        this.getGameModel().getGame().getWave().setSpeedToAllAliens(speed);
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
