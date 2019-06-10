package fr.space.views;

import fr.space.controllers.ControllerSpace;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class View extends JFrame implements Observer {
    private ControllerSpace controller;
    private String title = "";
    private int width, height;


    public View(ControllerSpace controller, String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        this.controller = controller;
        this.controller.getModel().addObserver(this);
    }


    public abstract void initWindow();


    public abstract void update(Observable observable, Object o);

    public ControllerSpace getController() {
        return this.controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }


    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }


    public void setHeight(int height) {
        this.height = height;
    }
}
