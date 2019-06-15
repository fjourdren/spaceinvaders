package fr.space.views.menuActions;

import fr.space.controllers.ControllerSpace;

import  java.awt.event.*;

public class PauseAction implements ActionListener {
    ControllerSpace controllerSpace;

    public PauseAction(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerSpace().getModel().getGame().turnPause();
    }

    public ControllerSpace getControllerSpace() {
        return controllerSpace;
    }

    public void setControllerSpace(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }
}
