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


    /*
     * Constructors
     * */
    public JFileChooserConfigAction(ConfigView parent, String toSet) {
        this.setParent(parent);
        this.setToSet(toSet);
    }



    /*
     * Methods
     * */
    public void actionPerformed(ActionEvent e) {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter extensionsFilter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png"); // extensions autorisés pour les sprites

        // on applique le filtre d'extension et on demande que des fichiers
        jf.setFileFilter(extensionsFilter);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // on ouvre la fenêtre de parcours des fichiers et on attend une valeur
        if(jf.showOpenDialog((Component) e.getSource()) == JFileChooser.APPROVE_OPTION) {
            //File dir = jf.getCurrentDirectory();
            File file = jf.getSelectedFile();


            // en fonction de l'action configurer on va modifier le bon sprite
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



    /*
     * GETTER & SETTER
     * */
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
