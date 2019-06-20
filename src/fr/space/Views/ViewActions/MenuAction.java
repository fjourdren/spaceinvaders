package fr.space.Views.ViewActions;

import fr.space.Controllers.ControllerGame;
import fr.space.Main.Run;

import javax.swing.*;
import javax.swing.event.*;

// on utilise cette classe différente des ActionListener car Swing différencie les éléments du menu et les sous-items du menu
public class MenuAction implements MenuListener {

    private ControllerGame controllerGame;


    /*
     * Constructors
     * */
    public MenuAction(ControllerGame controllerGame) {
        this.setControllerGame(controllerGame);
    }



    /*
     * Methods
     * */
    @Override
    public void menuSelected(MenuEvent e) {
        JMenu source = (JMenu) e.getSource();

        // on détecte si le joueur a cliqué sur le menu de configuration ou sur quitter en comparent le getText des éléments
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
