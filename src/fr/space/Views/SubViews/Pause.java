package fr.space.Views.SubViews;

import fr.space.Views.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class Pause extends JPanel {

    private BoardPanel parentBoard;


    /*
     * Constructors
     * */
    public Pause(BoardPanel parentBoard) {
        this.setParentBoard(parentBoard);
    }



    /*
     * Methods
     * */
    public void render(Graphics g) {
        int scoresSize = 50; // taille du texte de pause

        String pause = "Pause";

        // pause render
        g.setFont(new Font("Serif", Font.BOLD, scoresSize));
        g.setColor(Color.WHITE);

        // calcul de la taille du rendu
        int widthPause = g.getFontMetrics().stringWidth(pause);
        int heightPause = g.getFontMetrics().stringWidth(pause);

        // rendu du texte
        g.drawString(pause, (this.getParentBoard().getWidth() - widthPause) / 2, (this.getParentBoard().getHeight() - heightPause) / 2);
    }



    /*
     * GETTER & SETTER
     * */
    public BoardPanel getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }
}
