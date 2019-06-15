package fr.space.views;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {

    private BoardPanel parentBoard;

    public GameOver(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }

    public void render(Graphics g) {
        final int GameOverSize = 50;

        String gameover = "Game Over";
        String restartGame = "Press R to play";

        // Game Over render
        g.setFont(new Font("Serif", Font.BOLD, GameOverSize));
        g.setColor(Color.RED);

        int widthGameOver = g.getFontMetrics().stringWidth(gameover);
        int heightGameOver = g.getFontMetrics().stringWidth(gameover);

        g.drawString(gameover, (this.getParentBoard().getWidth() - widthGameOver) / 2, (this.getParentBoard().getHeight() - heightGameOver) / 2);


        // play key render
        g.setFont(new Font("Serif", Font.BOLD, (int) (GameOverSize * 0.6)));
        g.setColor(Color.WHITE);

        int widthRestartGame = g.getFontMetrics().stringWidth(restartGame);
        int heightRestartGame = g.getFontMetrics().stringWidth(restartGame);
        int yRestartGame = ((this.getParentBoard().getHeight() - heightGameOver) / 2) + heightRestartGame + 20;

        g.drawString(restartGame, (this.getParentBoard().getWidth() - widthRestartGame) / 2, yRestartGame);
    }

    public BoardPanel getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }
}
