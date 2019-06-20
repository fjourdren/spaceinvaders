package fr.space.Views;

import fr.space.Models.Entity;
import fr.space.Controllers.ControllerGame;
import fr.space.Views.SubViews.GameOver;
import fr.space.Views.SubViews.Pause;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private ControllerGame controller;
    private GameOver gameover;
    private Pause pause;


    /*
     * Constructors
     * */
    public BoardPanel(ControllerGame controller, int width, int height) {
        this.setController(controller);
        this.setGameover(new GameOver(this));
        this.setPause(new Pause(this));

        this.setPreferredSize(new Dimension(width, height)); // met la taille de la JFrame en place
    }



    /*
     * Methods
     * */
    // rendu principal du jeu
    @Override
    public void paint(Graphics g) {
        super.paint(g);


        // fond par défaut en noir
        this.setBackground(Color.BLACK);


        // on met le background
        BufferedImage backgroundImage = this.getController().getGameModel().getBackground().getImage();
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);


        // rendu de toutes les entités
        for (Entity e: this.getController().getEntities()) {
            e.render(g);
        }


        // rendu de gameover si on a perdu
        if(this.getController().getGameModel().isLose())
            this.getGameover().render(g);
        // sinon si le joueur est en pause, rendu de pause
        else if(this.getController().getGameModel().isPause())
            this.getPause().render(g);


        // fix lags (désactive le caps des FPS de SWING lorsque la souris ne bouge pas)
        Toolkit.getDefaultToolkit().sync();
    }



    /*
     * GETTER & SETTER
     * */
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
