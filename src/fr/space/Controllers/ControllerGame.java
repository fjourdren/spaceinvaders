package fr.space.Controllers;

import fr.space.Models.Keyboard;
import fr.space.Models.Spaceship;
import fr.space.Models.Wave;
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


    public long getShootInterval() {
        return this.getGameModel().getPlayer().getShootInterval();
    }


    public float getAliensSpeed() {
        return this.getGameModel().getWave().getAlien().getSpeed();
    }


    public int getAliensRow() {
        return this.getGameModel().getWave().getRow();
    }


    public int getAliensCol() {
        return this.getGameModel().getWave().getCol();
    }
}
