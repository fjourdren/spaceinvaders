package fr.space.views;

import javax.swing.*;
import java.awt.*;

public class Pause extends JPanel {

    private BoardPanel parentBoard;

    public Pause(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }

    public void render(Graphics g) {
        int scoresSize = 50;

        g.setFont(new Font("TimesRoman", Font.BOLD, scoresSize));
        g.setColor(Color.WHITE);

        String pause = "Pause";

        int widthPause = g.getFontMetrics().stringWidth(pause);
        int heightPause = g.getFontMetrics().stringWidth(pause);

        g.drawString(pause, (this.getParentBoard().getWidth() - widthPause) / 2, (this.getParentBoard().getHeight() - heightPause)/ 2);
    }

    public BoardPanel getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }
}
