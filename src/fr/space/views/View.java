package fr.space.views;

import fr.space.controllers.Controller;

import java.util.Observable;
import java.util.Observer;

public abstract class View implements Observer {
    private Controller controller;
    private String title = "";


    public View(Controller controller, String title) {
        this.title = title;

        this.controller = controller;
        this.controller.getModel().addObserver(this);

        this.initComposant();
    }


    public Controller getController() {
        return this.controller;
    }


    public abstract void initComposant();


    public abstract void update(Observable observable, Object o);
}
