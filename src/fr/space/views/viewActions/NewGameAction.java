package fr.space.views.viewActions;

import fr.space.controllers.ControllerSpace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameAction implements ActionListener {
    private ControllerSpace controllerSpace;

    public NewGameAction(ControllerSpace controllerSpace) {
        this.setControllerSpace(controllerSpace);
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerSpace().getGameModel().getGame().reset();
    }

    public ControllerSpace getControllerSpace() {
        return controllerSpace;
    }

    public void setControllerSpace(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }
}