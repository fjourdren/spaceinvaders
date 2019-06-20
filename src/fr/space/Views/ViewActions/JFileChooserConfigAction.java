package fr.space.Views.ViewActions;

import fr.space.Views.ConfigView;

import javax.swing.*;
import java.awt.Component;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class JFileChooserConfigAction implements ActionListener {

    private ConfigView parent;
    private String toSet;

    public JFileChooserConfigAction(ConfigView parent, String toSet) {
        this.setParent(parent);
        this.setToSet(toSet);
    }


    public void actionPerformed(ActionEvent e) {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter extensionsFilter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png");

        jf.setFileFilter(extensionsFilter);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(jf.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
            //File dir = jf.getCurrentDirectory();
            File file = jf.getSelectedFile();

            switch(this.getToSet()) {
                case "bullet":
                    this.getParent().setBulletSpritePath(file.getPath());
                    break;
                case "alien":
                    this.getParent().setAlienSpritePath(file.getPath());
                    break;
                case "spaceship":
                    this.getParent().setSpaceshipSpritePath(file.getPath());
                    break;
                case "explosion":
                    this.getParent().setExplosionSpritePath(file.getPath());
                    break;
                case "background":
                    this.getParent().setBackgroundPath(file.getPath());
                    break;
            }
        }
    }


    public ConfigView getParent() {
        return parent;
    }

    public void setParent(ConfigView parent) {
        this.parent = parent;
    }

    public String getToSet() {
        return toSet;
    }

    public void setToSet(String toSet) {
        this.toSet = toSet;
    }
}
