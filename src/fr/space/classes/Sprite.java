package fr.space.classes;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

public class Sprite {
    private BufferedImage image;
    private int xDimension, yDimension;

    public Sprite(String imagePath) {
        this.image = Sprite.loadImage(imagePath);
        this.calculateSize();
    }


    public Sprite(BufferedImage image) {
        this.image = image;
        this.calculateSize();
    }


    public static BufferedImage loadImage(String imagePath) {
        BufferedImage output = null;

        try {
            output = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            JOptionPane optionPane = new JOptionPane("Erreur sur le fichier " + imagePath, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Erreur");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);

            System.exit(0);
        }

        return output;
    }


    public void calculateSize() {
        this.xDimension = this.image.getWidth();
        this.yDimension = this.image.getHeight();
    }


    public BufferedImage getImage() {
        return image;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }


    public int getxDimension() {
        return xDimension;
    }


    public void setxDimension(int xDimension) {
        this.xDimension = xDimension;
    }


    public int getyDimension() {
        return yDimension;
    }


    public void setyDimension(int yDimension) {
        this.yDimension = yDimension;
    }
}
