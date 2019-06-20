package fr.space.Views.viewActions;

import fr.space.Controllers.ControllerGame;
import fr.space.Main.Run;

import javax.swing.*;
import javax.swing.event.*;

public class MenuAction implements MenuListener {
    private ControllerGame controllerGame;

    public MenuAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();
        if(source.getText() == "Configurations") {
            Run.openConfigView(this.getControllerGame());
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

    public ControllerGame getControllerGame() {
        return controllerGame;
    }

    public void setControllerGame(ControllerGame controllerGame) {
        this.controllerGame = controllerGame;
    }
}
