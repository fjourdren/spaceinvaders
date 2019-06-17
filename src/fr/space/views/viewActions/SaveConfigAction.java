package fr.space.views.viewActions;

import fr.space.views.ConfigView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveConfigAction implements ActionListener {

    private ConfigView configView;

    public SaveConfigAction(ConfigView configView) {
        this.setConfigView(configView);
    }


    public void actionPerformed(ActionEvent e) {
        ConfigView configView = this.getConfigView();

        /*configView.setBulletSpritePath();
        configView.setSpaceshipSpritePath();
        configView.setExplosionSpritePath();
        configView.setBackgroundPath();*/

        //long intervalShoot = Integer.parseInt(configView.getTextFieldInterval().getText());
        //configView.getController().applyConfigIntervalShoot(intervalShoot);


        if(!configView.getTextFieldNumber().getText().isEmpty()) {
            int numberAliens = Integer.parseInt(configView.getTextFieldNumber().getText());
            configView.getController().applyConfigNumber(numberAliens);
        }

        if(!configView.getTextFieldSpeed().getText().isEmpty()) {
            float speedAliens = Float.parseFloat(configView.getTextFieldSpeed().getText());
            configView.getController().applyConfigSpeed(speedAliens);
        }

    }


    public ConfigView getConfigView() {
        return configView;
    }

    public void setConfigView(ConfigView configView) {
        this.configView = configView;
    }
}
