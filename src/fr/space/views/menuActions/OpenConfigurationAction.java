package fr.space.views.menuActions;

import fr.space.controllers.ControllerSpace;

import javax.swing.*;
import javax.swing.event.*;

public class OpenConfigurationAction implements MenuListener {
    private ControllerSpace controllerSpace;

    public OpenConfigurationAction(ControllerSpace controllerSpace) {
        this.setControllerSpace(controllerSpace);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();
        if(source.getText() == "Configurations") {
            System.out.println("Config");
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
