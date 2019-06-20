package fr.space.Views;

import fr.space.Classes.Entity;
import fr.space.Controllers.ControllerGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerGame controller;
    private GameOver gameover;
    private Pause pause;

    public BoardPanel(ControllerGame controller, int width, int height) {
        this.setController(controller);
        this.setGameover(new GameOver(this));
        this.setPause(new Pause(this));

        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.setBackground(Color.BLACK);

        // set background
        BufferedImage backgroundImage = this.getController().getGameModel().getBackground().getImage();
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);

        for (Entity e: this.getController().getGameModel().getEntities()) {
            e.render(g);
        }

        if(this.getController().getGameModel().isLose())
            this.getGameover().render(g);
        else if(this.getController().getGameModel().isPause())
            this.getPause().render(g);

        // fix lags
        Toolkit.getDefaultToolkit().sync();
    }




    public ControllerGame getController() {
        return controller;
    }

    public void setController(ControllerGame controller) {
        this.controller = controller;
    }

    public GameOver getGameover() {
        return gameover;
    }

    public void setGameover(GameOver gameover) {
        this.gameover = gameover;
    }

    public Pause getPause() {
        return pause;
    }

    public void setPause(Pause pause) {
        this.pause = pause;
    }
}
