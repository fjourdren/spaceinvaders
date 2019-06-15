package fr.space.views.menuActions;

import fr.space.controllers.ControllerSpace;

import javax.swing.event.*;
import javax.swing.*;

public class QuitAction implements MenuListener {
    ControllerSpace controllerSpace;

    public QuitAction(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();
        if(source.getText() == "Quitter") {
            System.exit(0);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public ControllerSpace getControllerSpace() {
        return controllerSpace;
    }

    public void setControllerSpace(ControllerSpace controllerSpace) {
        this.controllerSpace = controllerSpace;
    }
}
