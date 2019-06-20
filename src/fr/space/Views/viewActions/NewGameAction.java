package fr.space.Views.viewActions;

import fr.space.Controllers.ControllerGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameAction implements ActionListener {
    private ControllerGame controllerGame;

    public NewGameAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerGame().getGameModel().reset();
    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }
}