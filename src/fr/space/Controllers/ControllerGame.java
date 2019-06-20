package fr.space.Controllers;

import fr.space.Classes.Keyboard;
import fr.space.Classes.Spaceship;
import fr.space.Classes.Wave;
import fr.space.Views.BoardPanel;
import fr.space.Models.GameModel;

public class ControllerGame extends Controller {

    public ControllerGame(GameModel gameModel) {
        super(gameModel);
    }

    public void applyConfigIntervalShoot(long interval) {
        Spaceship.setShootInterval(interval);
    }


    public void applyConfigNumberAliens(int row, int col) {
        Wave wave = this.getGameModel().getDefaultWave();

        wave.setRow(row);
        wave.setCol(col);
    }

    public void applyConfigSpeed(float speed) {
        this.getGameModel().getWave().setSpeedToAllAliens(speed);
    }


    public void setBoard(BoardPanel boardPanel) {
        this.getGameModel().setBoardPanel(boardPanel);
    }


    public BoardPanel getBoardPanel() { return this.getGameModel().getBoardPanel(); }


    public void setKeyboard(Keyboard keyboard) {
        this.getGameModel().setKeyboard(keyboard);
    }


    public int getScore() {
        return this.getGameModel().getScore();
    }


    public int getLevel() {
        return this.getGameModel().getLevel();
    }


    public int getNbAlien() {
        return this.getGameModel().getNbAlien();
    }
}
