package fr.space.Views.ViewActions;

import fr.space.Utils.RessourceLoader;
import fr.space.Models.Sprite;
import fr.space.Views.ConfigView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class SaveConfigAction implements ActionListener {

    private ConfigView configView;


    /*
     * Constructors
     * */
    public SaveConfigAction(ConfigView configView) {
        this.setConfigView(configView);
    }



    /*
     * Methods
     * */
    public void actionPerformed(ActionEvent e) {
        boolean canClose = true;

        // config bullet sprite
        if(this.getConfigView().getBulletSpritePath() != null) {
            BufferedImage newImage = RessourceLoader.loadBufferedImage(this.getConfigView().getBulletSpritePath());
            Sprite.getSpriteBullet().setImage(newImage);
        }


        // config alien sprite
        if(this.getConfigView().getAlienSpritePath() != null) {
            BufferedImage newImage = RessourceLoader.loadBufferedImage(this.getConfigView().getAlienSpritePath());
            Sprite.getSpriteAlien().setImage(newImage);
        }


        // config spaceship sprite
        if(this.getConfigView().getSpaceshipSpritePath() != null) {
            BufferedImage newImage = RessourceLoader.loadBufferedImage(this.getConfigView().getBulletSpritePath());
            Sprite.getSpriteShip().setImage(newImage);
        }


        // config explosion sprite
        if(this.getConfigView().getExplosionSpritePath() != null) {
            BufferedImage newImage = RessourceLoader.loadBufferedImage(this.getConfigView().getBulletSpritePath());
            Sprite.getSpriteExplosion().setImage(newImage);
        }


        // config background image
        if(this.getConfigView().getBackgroundPath() != null) {
            BufferedImage newImage = RessourceLoader.loadBufferedImage(this.getConfigView().getBackgroundPath());
            Sprite.getBackground().setImage(newImage);
        }


        // config shooting interval
        if(!this.getConfigView().getTextFieldInterval().getText().isEmpty()) {
            long interval = Long.parseLong(this.getConfigView().getTextFieldInterval().getText());

            this.getConfigView().getController().applyConfigIntervalShoot(interval);
        }


        // config du nombre de ligne et de colone de la prochaine wave
        if(!this.getConfigView().getTextFieldRow().getText().isEmpty()
                && !this.getConfigView().getTextFieldCol().getText().isEmpty()) {

            // récupère les valeurs dans des variables
            int row = Integer.parseInt(this.getConfigView().getTextFieldRow().getText());
            int col = Integer.parseInt(this.getConfigView().getTextFieldCol().getText());

            // on vérifie que c'est bien supérieur à 0
            if(row <= 0 || col <= 0) {
                javax.swing.JOptionPane.showMessageDialog(null, "La vague doit avoir plus de 0 ligne et colonne");
                canClose = false;
            } else {
                if(this.getConfigView().getController().getGameModel().getDefaultWave().getRow() != row
                        || this.getConfigView().getController().getGameModel().getDefaultWave().getCol() != col) { // si différent de la valeur déjà en place, on applique
                    this.getConfigView().getController().applyConfigNumberAliens(row, col);

                    javax.swing.JOptionPane.showMessageDialog(null, "Changement de taille de la prochaine vague uniquement");
                } else {
                    canClose = false;
                }
            }

        }


        // config de la vitesse des aliens
        if(!this.getConfigView().getTextFieldSpeed().getText().isEmpty()) {
            float speedAliens = Float.parseFloat(this.getConfigView().getTextFieldSpeed().getText());

            this.getConfigView().getController().applyConfigSpeed(speedAliens);
        }


        // si les paramètres sont correctement mis en place, on ferme la fenêtre
        if(canClose) {
            this.getConfigView().dispatchEvent(new WindowEvent(this.getConfigView(), WindowEvent.WINDOW_CLOSING));
        }

    }



    /*
     * GETTER & SETTER
     * */
    public ConfigView getConfigView() {
        return configView;
    }

    public void setConfigView(ConfigView configView) {
        this.configView = configView;
    }
}
