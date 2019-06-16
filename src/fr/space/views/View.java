package fr.space.views;

import fr.space.controllers.ControllerSpace;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public abstract class View extends JFrame implements Observer {
    private ControllerSpace controller;

    public View(ControllerSpace controller, String title, int width, int height) {
        this.setController(controller);
        this.getController().getGameModel().addObserver(this);


        // frame
        this.setSize(width, height);
        this.setTitle(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationByPlatform(true);
    }


    public abstract void buildWindow();

    public abstract void update(Observable observable, Object o);


    public ControllerSpace getController() {
        return this.controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }
}
