package fr.space.views;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {

    private BoardPanel parentBoard;

    public Score(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }

    public void render(Graphics g) {
        int scoresSize = 25;

        g.setFont(new Font("TimesRoman", Font.BOLD, scoresSize));
        g.setColor(Color.WHITE);

        String score = String.valueOf(this.getParentBoard().getController().getModel().getGame().getScore());

        int widthScore = g.getFontMetrics().stringWidth(score);

        g.drawString(score, this.getParentBoard().getWidth() - widthScore - 5, scoresSize + 5);
    }

    public BoardPanel getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(BoardPanel parentBoard) {
        this.parentBoard = parentBoard;
    }
}
