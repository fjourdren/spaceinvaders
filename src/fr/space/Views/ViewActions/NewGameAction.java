package fr.space.Views.ViewActions;

import fr.space.Controllers.ControllerGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameAction implements ActionListener {

    private ControllerGame controllerGame;


    /*
     * Constructors
     * */
    public NewGameAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }



    /*
     * Methods
     * */
    // si l'action new game est appelé depuis le menu, alors on reset la game
    public void actionPerformed(ActionEvent e) {
        this.getControllerGame().getGameModel().reset();
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