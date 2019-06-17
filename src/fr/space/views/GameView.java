package fr.space.views;

import fr.space.classes.Keyboard;
import fr.space.controllers.ControllerSpace;
import fr.space.views.viewActions.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class GameView extends View {

    private JPanel infoPanel;
    private BoardPanel boardPanel;

    private int widthBoard, widthInfo, heightWindow;

    private JLabel scoreValue, aliensValue, levelValue;

    public GameView(ControllerSpace controller, String title, int widthBoard, int widthInfo, int heightWindow) {
        super(controller, title, widthBoard + widthInfo, heightWindow);

        this.setWidthBoard(widthBoard);
        this.setWidthInfo(widthInfo);
        this.setHeightWindow(heightWindow);


        this.buildWindow();

        this.runKeyboard();
    }

    private JMenuBar buildMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Jeu");
        JMenuItem itemNewGame = new JMenuItem("Nouvelle Partie");
        JMenuItem itemPause = new JMenuItem("Pause");

        JMenu menuOpenConfiguration = new JMenu("Configurations");
        JMenu menuQuitter = new JMenu("Quitter");


        // build menu
        menuGame.add(itemNewGame);
        menuGame.add(itemPause);

        menuBar.add(menuGame);
        menuBar.add(menuOpenConfiguration);
        menuBar.add(menuQuitter);


        // setup action
        NewGameAction actionNewGame = new NewGameAction(this.getController());
        itemNewGame.addActionListener(actionNewGame);

        PauseAction actionPause = new PauseAction(this.getController());
        itemPause.addActionListener(actionPause);

        MenuAction actionOpenConfiguration = new MenuAction(this.getController());
        menuOpenConfiguration.addMenuListener(actionOpenConfiguration);

        MenuAction menuAction = new MenuAction(this.getController());
        menuQuitter.addMenuListener(menuAction);

        return menuBar;
    }

    public JPanel buildInfoPanel(int width, int height) {
        JPanel infos = new JPanel();
        infos.setLayout(new GridLayout(3,2));

        // labels
        JLabel scoreLabel = new JLabel("Score :");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel aliensLabel = new JLabel("Nb Aliens :");
        aliensLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel levelLabel = new JLabel("Niveau :");
        levelLabel.setHorizontalAlignment(JLabel.CENTER);


        // values labels
        this.setScoreValue(new JLabel("0"));
        this.getScoreValue().setHorizontalAlignment(JLabel.CENTER);

        this.setAliensValue(new JLabel("4"));
        this.getAliensValue().setHorizontalAlignment(JLabel.CENTER);

        this.setLevelValue(new JLabel("5"));
        this.getLevelValue().setHorizontalAlignment(JLabel.CENTER);


        // ajout des éléments
        infos.add(scoreLabel);
        infos.add(this.getScoreValue());

        infos.add(aliensLabel);
        infos.add(this.getAliensValue());

        infos.add(levelLabel);
        infos.add(this.getLevelValue());


        infos.setPreferredSize(new Dimension(width, height));

        infos.setVisible(true);

        return infos;
    }


    public void buildWindow() {
        // === init ===
        this.setBoardPanel(new BoardPanel(this.getController(), this.getWidthBoard(), this.getHeightWindow()));
        this.getController().setBoard(this.getBoardPanel());

        this.setInfoPanel(this.buildInfoPanel(this.getWidthInfo(), this.getHeightWindow()));

        JMenuBar menu = this.buildMenu();


        // === building ===
        // menu
        this.setJMenuBar(menu);

        // content
        this.add(this.getBoardPanel());
        this.add(this.getInfoPanel());

        // structure
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.setVisible(true);
    }


    public void runKeyboard() {
        // keyboard
        Keyboard keyboard = new Keyboard();
        this.addKeyListener(keyboard);
        this.getController().setKeyboard(keyboard);
    }


    public void update(Observable observable, Object o) {
        this.scoreValue.setText(String.valueOf(this.getController().getScore()));
    }


    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(JPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public int getWidthBoard() {
        return widthBoard;
    }

    public void setWidthBoard(int widthBoard) {
        this.widthBoard = widthBoard;
    }

    public int getWidthInfo() {
        return widthInfo;
    }

    public void setWidthInfo(int widthInfo) {
        this.widthInfo = widthInfo;
    }

    public int getHeightWindow() {
        return heightWindow;
    }

    public void setHeightWindow(int heightWindow) {
        this.heightWindow = heightWindow;
    }

    public JLabel getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(JLabel scoreValue) {
        this.scoreValue = scoreValue;
    }

    public JLabel getAliensValue() {
        return aliensValue;
    }

    public void setAliensValue(JLabel aliensValue) {
        this.aliensValue = aliensValue;
    }

    public JLabel getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(JLabel levelValue) {
        this.levelValue = levelValue;
    }
}
