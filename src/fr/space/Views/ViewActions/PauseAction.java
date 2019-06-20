package fr.space.Views.ViewActions;

import fr.space.Controllers.ControllerGame;

import  java.awt.event.*;

public class PauseAction implements ActionListener {

    private ControllerGame controllerGame;


    /*
     * Constructors
     * */
    public PauseAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }



    /*
     * Methods
     * */
    // on passe en mode pause via le menu de la fenêtre principale
    public void actionPerformed(ActionEvent e) {
        this.getControllerGame().getGameModel().turnPause();
    }



    /*
     * GETTER & SETTER
     * */
    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }
}
