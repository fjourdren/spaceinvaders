package fr.space.views;

import fr.space.classes.Sprite;
import fr.space.controllers.ControllerSpace;
import fr.space.views.viewActions.JFileChooserConfigAction;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ConfigView extends View {



    public ConfigView(ControllerSpace controller, String title, int width, int height) {
        super(controller, title, width, height);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.buildWindow();
    }


    public void buildWindow() {
        // === building ===
        JPanel panelStructure = new JPanel();

        panelStructure.setLayout(new BorderLayout());


        // generate top
        JPanel top = new JPanel();

        top.setLayout(new GridLayout(8,2));
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 3, 10));



        // generate swing elements
        JLabel labelSpriteBullet = new JLabel("Sprite Bullet :");
        JButton buttonParcourirBullet = new JButton("Parcourir...");

        buttonParcourirBullet.addActionListener(new JFileChooserConfigAction(Sprite.getSpriteBullet()));

        top.add(labelSpriteBullet);
        top.add(buttonParcourirBullet);


        JLabel labelSpriteAlien = new JLabel("Sprite Alien :");
        JButton buttonParcourirALien = new JButton("Parcourir...");

        top.add(labelSpriteAlien);
        top.add(buttonParcourirALien);


        JLabel labelSpriteSpaceShip = new JLabel("Sprite SpaceShip :");
        JButton buttonParcourirSpaceShip = new JButton("Parcourir...");

        top.add(labelSpriteSpaceShip);
        top.add(buttonParcourirSpaceShip);


        JLabel labelSpriteExplosion = new JLabel("Sprite Explosion :");
        JButton buttonParcourirExplosion = new JButton("Parcourir...");

        top.add(labelSpriteExplosion);
        top.add(buttonParcourirExplosion);


        JLabel labelBackground = new JLabel("Background :");
        JButton buttonParcourirBackground = new JButton("Parcourir...");

        top.add(labelBackground);
        top.add(buttonParcourirBackground);


        JLabel labelInterval = new JLabel("Intervalle tire :");
        JTextField textFieldInterval = new JTextField();

        top.add(labelInterval);
        top.add(textFieldInterval);


        JLabel labelNumber = new JLabel("Nombre Aliens :");
        JTextField textFieldNumber = new JTextField();

        top.add(labelNumber);
        top.add(textFieldNumber);


        JLabel labelSpeed = new JLabel("Vitesse des Aliens :");
        JTextField textFieldSpeed = new JTextField();

        top.add(labelSpeed);
        top.add(textFieldSpeed);




        // generate bot
        JPanel bot = new JPanel();

        bot.add(new JButton("Sauvegarder"));


        panelStructure.add(top, BorderLayout.CENTER);
        panelStructure.add(bot, BorderLayout.SOUTH);





        /*panelStructure.setLayout(new GridLayout(2,1));


        JPanel panelConfig = new JPanel();
        panelConfig.add(new JLabel("test"));



        panelConfig.add(jLabelOriginePres);


        panelStructure.add(panelConfig);

        //save button
        JButton saveButton = new JButton("Sauvegarder");
        saveButton.setPreferredSize(new Dimension(100,100));
        panelStructure.add(saveButton);*/


        this.add(panelStructure);



        this.setVisible(true);
    }


    public void update(Observable observable, Object o) {

    }
}
