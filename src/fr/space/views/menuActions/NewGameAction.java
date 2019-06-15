package fr.space.views.menuActions;

import fr.space.controllers.ControllerSpace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameAction implements ActionListener {
    ControllerSpace controllerSpace;

    public NewGameAction(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }

    public void actionPerformed(ActionEvent e) {
        this.getControllerSpace().getModel().getGame().reset();
    }

    public ControllerSpace getControllerSpace() {
        return controllerSpace;
    }

    public void setControllerSpace(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }
}