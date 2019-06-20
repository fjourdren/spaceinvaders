package fr.space.Views;

import fr.space.Controllers.ControllerGame;
import fr.space.Views.ViewActions.JFileChooserConfigAction;
import fr.space.Views.ViewActions.SaveConfigAction;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ConfigView extends View {

    private String bulletSpritePath = null;
    private String alienSpritePath = null;
    private String spaceshipSpritePath = null;
    private String explosionSpritePath = null;
    private String backgroundPath = null;

    private long intervalShoot;
    private int numberAliens;
    private float speedAliens;


    private JTextField textFieldInterval;
    private JTextField textFieldRow;
    private JTextField textFieldCol;
    private JTextField textFieldSpeed;


    public ConfigView(ControllerGame controller, String title, int width, int height) {
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

        top.setLayout(new GridLayout(9,2));
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 3, 10));



        // generate swing elements
        JLabel labelSpriteBullet = new JLabel("Sprite Bullet :");
        JButton buttonParcourirBullet = new JButton("Parcourir...");

        buttonParcourirBullet.addActionListener(new JFileChooserConfigAction(this, "bullet"));

        top.add(labelSpriteBullet);
        top.add(buttonParcourirBullet);


        JLabel labelSpriteAlien = new JLabel("Sprite Alien :");
        JButton buttonParcourirALien = new JButton("Parcourir...");

        buttonParcourirALien.addActionListener(new JFileChooserConfigAction(this, "alien"));

        top.add(labelSpriteAlien);
        top.add(buttonParcourirALien);


        JLabel labelSpriteSpaceShip = new JLabel("Sprite SpaceShip :");
        JButton buttonParcourirSpaceShip = new JButton("Parcourir...");

        buttonParcourirSpaceShip.addActionListener(new JFileChooserConfigAction(this, "spaceship"));

        top.add(labelSpriteSpaceShip);
        top.add(buttonParcourirSpaceShip);


        JLabel labelSpriteExplosion = new JLabel("Sprite Explosion :");
        JButton buttonParcourirExplosion = new JButton("Parcourir...");

        buttonParcourirExplosion.addActionListener(new JFileChooserConfigAction(this, "explosion"));

        top.add(labelSpriteExplosion);
        top.add(buttonParcourirExplosion);


        JLabel labelBackground = new JLabel("Arrière plan :");
        JButton buttonParcourirBackground = new JButton("Parcourir...");

        buttonParcourirBackground.addActionListener(new JFileChooserConfigAction(this, "background"));

        top.add(labelBackground);
        top.add(buttonParcourirBackground);


        JLabel labelInterval = new JLabel("Intervalle de tir (en nanoseconde) :");
        this.setTextFieldInterval(new JTextField(String.valueOf(this.getController().getShootInterval())));

        top.add(labelInterval);
        top.add(this.getTextFieldInterval());


        JLabel labelNumberRowAliens = new JLabel("Nombre lignes d'aliens :");
        this.setTextFieldRow(new JTextField(String.valueOf(this.getController().getAliensRow())));

        top.add(labelNumberRowAliens);
        top.add(this.getTextFieldRow());


        JLabel labelNumberColAliens = new JLabel("Nombre colonnes d'aliens :");
        this.setTextFieldCol(new JTextField(String.valueOf(this.getController().getAliensCol())));

        top.add(labelNumberColAliens);
        top.add(this.getTextFieldCol());


        JLabel labelSpeed = new JLabel("Vitesse des Aliens :");
        this.setTextFieldSpeed(new JTextField(String.valueOf(this.getController().getAliensSpeed())));

        top.add(labelSpeed);
        top.add(this.getTextFieldSpeed());





        // generate bot
        JPanel bot = new JPanel();

        JButton saveButton = new JButton("Sauvegarder");
        saveButton.addActionListener(new SaveConfigAction(this));
        bot.add(saveButton);


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


    public String getBulletSpritePath() {
        return bulletSpritePath;
    }

    public void setBulletSpritePath(String bulletSpritePath) {
        this.bulletSpritePath = bulletSpritePath;
    }

    public String getSpaceshipSpritePath() {
        return spaceshipSpritePath;
    }

    public void setSpaceshipSpritePath(String spaceshipSpritePath) {
        this.spaceshipSpritePath = spaceshipSpritePath;
    }

    public String getExplosionSpritePath() {
        return explosionSpritePath;
    }

    public void setExplosionSpritePath(String explosionSpritePath) {
        this.explosionSpritePath = explosionSpritePath;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public long getIntervalShoot() {
        return intervalShoot;
    }

    public void setIntervalShoot(long intervalShoot) {
        this.intervalShoot = intervalShoot;
    }

    public int getNumberAliens() {
        return numberAliens;
    }

    public void setNumberAliens(int numberAliens) {
        this.numberAliens = numberAliens;
    }

    public float getSpeedAliens() {
        return speedAliens;
    }

    public void setSpeedAliens(float speedAliens) {
        this.speedAliens = speedAliens;
    }

    public JTextField getTextFieldInterval() {
        return textFieldInterval;
    }

    public void setTextFieldInterval(JTextField textFieldInterval) {
        this.textFieldInterval = textFieldInterval;
    }

    public JTextField getTextFieldSpeed() {
        return textFieldSpeed;
    }

    public void setTextFieldSpeed(JTextField textFieldSpeed) {
        this.textFieldSpeed = textFieldSpeed;
    }

    public JTextField getTextFieldRow() {
        return textFieldRow;
    }

    public void setTextFieldRow(JTextField textFieldRow) {
        this.textFieldRow = textFieldRow;
    }

    public JTextField getTextFieldCol() {
        return textFieldCol;
    }

    public void setTextFieldCol(JTextField textFieldCol) {
        this.textFieldCol = textFieldCol;
    }

    public String getAlienSpritePath() {
        return alienSpritePath;
    }

    public void setAlienSpritePath(String alienSpritePath) {
        this.alienSpritePath = alienSpritePath;
    }
}
