package fr.space.views;

import fr.space.classes.Entity;
import fr.space.controllers.ControllerSpace;

import java.awt.*;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerSpace controller;

    public BoardPanel(ControllerSpace controller) {
        this.controller = controller;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);

        for (Entity e: this.getController().getModel().getEntities()) {
            e.render(g);
        }

        /*g.setColor(Color.GREEN);
        // draw the rectangle here
        g.drawRect(50, 50, 200, 200);*/
    }

    public ControllerSpace getController() {
        return controller;
    }

    public void setController(ControllerSpace controller) {
        this.controller = controller;
    }
}
