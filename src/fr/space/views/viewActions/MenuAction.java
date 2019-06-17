package fr.space.views.viewActions;

import fr.space.controllers.ControllerSpace;
import fr.space.main.Run;

import javax.swing.*;
import javax.swing.event.*;

public class MenuAction implements MenuListener {
    private ControllerSpace controllerSpace;

    public MenuAction(ControllerSpace controllerSpace) {
        this.setControllerSpace(controllerSpace);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();
        if(source.getText() == "Configurations") {
            Run.openConfigView(this.getControllerSpace());
        } else if(source.getText() == "Quitter") {
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
