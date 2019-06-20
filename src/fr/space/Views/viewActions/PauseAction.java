package fr.space.Views.viewActions;

import fr.space.Controllers.ControllerGame;

import  java.awt.event.*;

public class PauseAction implements ActionListener {
    private ControllerGame controllerGame;

    public PauseAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerGame().getGameModel().turnPause();
    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }
}