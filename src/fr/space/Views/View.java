package fr.space.Views;

import fr.space.Controllers.ControllerGame;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public abstract class View extends JFrame implements Observer {

    private ControllerGame controller;


    /*
     * Constructors
     * */
    public View(ControllerGame controller, String title, int width, int height) {
        // on génère une JFrame commune à toutes nos fenêtres
        this.setController(controller);
        this.getController().getGameModel().addObserver(this);


        // frame
        this.setSize(width, height);
        this.setTitle(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);
    }



    /*
     * Methods
     * */
    public abstract void buildWindow();

    public abstract void update(Observable observable, Object o);



    /*
     * GETTER & SETTER
     * */
    public ControllerGame getController() {
        return this.controller;
    }

    public void setController(ControllerGame controller) {
        this.controller = controller;
    }
}
