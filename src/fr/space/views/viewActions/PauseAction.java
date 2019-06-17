package fr.space.views.viewActions;

import fr.space.controllers.ControllerSpace;

import  java.awt.event.*;

public class PauseAction implements ActionListener {
    private ControllerSpace controllerSpace;

    public PauseAction(ControllerSpace controllerSpace) {
        this.setControllerSpace(controllerSpace);
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerSpace().getGameModel().getGame().turnPause();
    }

    public ControllerSpace getControllerSpace() {
        return controllerSpace;
    }

    public void setControllerSpace(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }
}
