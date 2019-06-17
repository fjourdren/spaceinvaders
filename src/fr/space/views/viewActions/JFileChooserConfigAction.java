package fr.space.views.viewActions;

import fr.space.classes.RessourceLoader;
import fr.space.classes.Sprite;

import javax.swing.*;
import java.awt.Component;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class JFileChooserConfigAction implements ActionListener {

    private Sprite sprite;

    public JFileChooserConfigAction(Sprite sprite) {
        this.setSprite(sprite);
    }


    public void actionPerformed(ActionEvent e) {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter extensionsFilter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png");

        jf.setFileFilter(extensionsFilter);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(jf.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
            //File dir = jf.getCurrentDirectory();
            File file = jf.getSelectedFile();

            BufferedImage newImage = RessourceLoader.loadBufferedImage(file.getPath());
            this.getSprite().setImage(newImage);
        }
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
