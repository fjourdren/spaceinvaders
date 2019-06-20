package fr.space.Views.SubViews;

import fr.space.Views.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    private BoardPanel parentBoard;


    /*
     * Constructors
     * */
    public GameOver(BoardPanel parentBoard) {
        this.setParentBoard(parentBoard);
    }



    /*
     * Methods
     * */
    public void render(Graphics g) {
        final int GameOverSize = 50; // taille du GameOver

        String gameover = "Game Over";
        String restartGame = "Press R to play";


        /*
        game Over render
         */
        g.setFont(new Font("Serif", Font.BOLD, GameOverSize));
        g.setColor(Color.RED);

        // calcul de la taille que va prendre le texte une fois rendu pour le positioner
        int widthGameOver = g.getFontMetrics().stringWidth(gameover);
        int heightGameOver = g.getFontMetrics().stringWidth(gameover);

        // rendu du game over
        g.drawString(gameover, (this.getParentBoard().getWidth() - widthGameOver) / 2, (this.getParentBoard().getHeight() - heightGameOver) / 2);



        /*
        * Press R to play Render
        * */
        // redémarrer une partie label
        g.setFont(new Font("Serif", Font.BOLD, (int) (GameOverSize * 0.6))); // taille = 60% de GameOverSize
        g.setColor(Color.WHITE);

        // calcul de la taille que va prendre le texte une fois rendu pour le positioner
        int widthRestartGame = g.getFontMetrics().stringWidth(restartGame);
        int heightRestartGame = g.getFontMetrics().stringWidth(restartGame);

        // calcul hauteur du draw
        int yRestartGame = ((this.getParentBoard().getHeight() - heightGameOver) / 2) + heightRestartGame + 20;

        // rendu du texte de play
        g.drawString(restartGame, (this.getParentBoard().getWidth() - widthRestartGame) / 2, yRestartGame);
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
