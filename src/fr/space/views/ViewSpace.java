package fr.space.views;

import fr.space.classes.Keyboard;
import fr.space.controllers.ControllerSpace;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class ViewSpace extends View {

    private JPanel infoPanel;
    private BoardPanel boardPanel;

    private int widthBoard, widthInfo, heightWindow;

    public ViewSpace(ControllerSpace controller, String title, int widthBoard, int widthInfo, int heightWindow) {
        super(controller, title, widthBoard + widthInfo, heightWindow);

        this.widthBoard = widthBoard;
        this.widthInfo = widthInfo;
        this.heightWindow = heightWindow;


        this.buildWindow();

        this.runKeyboard();
    }

    private JMenuBar buildMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGame = new JMenu("Jeu");
        JMenuItem itemNewGame = new JMenuItem("Nouvelle Partie");
        JMenuItem itemPause = new JMenuItem("Pause");

        JMenu menuConfiguration = new JMenu("Configurations");
        JMenu menuQuitter = new JMenu("Quitter");

        menuGame.add(itemNewGame);
        menuGame.add(itemPause);

        menuBar.add(menuGame);
        menuBar.add(menuConfiguration);
        menuBar.add(menuQuitter);

        return menuBar;
    }

    public JPanel buildInfoPanel(int width, int height) {
        JPanel infos = new JPanel();

        infos.add(new JLabel("Score :"));
        infos.add(new JLabel("Nb Aliens :"));
        infos.add(new JLabel("Niveau :"));

        infos.setPreferredSize(new Dimension(width, height));

        return infos;
    }


    public void buildWindow() {
        // === init ===
        this.boardPanel = new BoardPanel(this.getController(), this.getWidthBoard(), this.getHeightWindow());
        this.getController().setBoard(this.getBoardPanel());

        this.infoPanel = this.buildInfoPanel(this.getWidthInfo(), this.getHeightWindow());

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
}
