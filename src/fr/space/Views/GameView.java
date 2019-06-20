package fr.space.Views;

import fr.space.Models.Keyboard;
import fr.space.Controllers.ControllerGame;
import fr.space.Views.ViewActions.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import javax.swing.border.*;

public class GameView extends View {

    private JPanel infoPanel;
    private BoardPanel boardPanel;

    private int widthBoard, widthInfo, heightWindow;

    private JLabel scoreValue, aliensValue, levelValue;


    /*
     * Constructors
     * */
    public GameView(ControllerGame controller, String title, int widthBoard, int widthInfo, int heightWindow) {
        super(controller, title, widthBoard + widthInfo, heightWindow);

        this.setWidthBoard(widthBoard);
        this.setWidthInfo(widthInfo);
        this.setHeightWindow(heightWindow);


        this.buildWindow(); // construit et met ensemble tous les éléments

        this.runKeyboard(); // exécute la récupération des touches du clavier
    }



    /*
     * Methods
     * */
    private JMenuBar buildMenu() {
        JMenuBar menuBar = new JMenuBar();

        // init menu
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


        // setup des actions
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

    // construction du JFrame d'information
    public JPanel buildInfoPanel(int width, int height) {
        Font font1 = new Font("Courier New", Font.BOLD, 25); // font pour le titre des informations
        Font font2 = new Font("Courier New", Font.ITALIC, 23); // font pour la valeur de l'information


        Border marginPanel = new EmptyBorder(0,10,0,5); // marges du JPanel d'info
        Border marginValues = new EmptyBorder(1,0,10,0); // marge de chaque valeur


        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS)); // on alignera tous les éléments sur l'axe Y

        // set panel's margins
        Border borderPanel = infos.getBorder();
        infos.setBorder(new CompoundBorder(borderPanel, marginPanel));

        /*
        labels des titres
        */
        // score
        JLabel scoreLabel = new JLabel("Score :");
        scoreLabel.setFont(font1);

        JLabel aliensLabel = new JLabel("Nb Aliens :");
        aliensLabel.setFont(font1);

        JLabel levelLabel = new JLabel("Niveau :");
        levelLabel.setFont(font1);


        /*
        values labels
        */
        // JLabel score value
        JLabel tmpScore = new JLabel("0");
        tmpScore.setFont(font2);

        Border borderScore = tmpScore.getBorder();
        tmpScore.setBorder(new CompoundBorder(borderScore, marginValues));

        this.setScoreValue(tmpScore);


        // JLabel nombre Alien value
        JLabel tmpAliens = new JLabel("0");
        tmpAliens.setFont(font2);

        Border borderAliens = tmpScore.getBorder();
        tmpAliens.setBorder(new CompoundBorder(borderAliens, marginValues));

        this.setAliensValue(tmpAliens);


        // JLabel niveau value
        JLabel tmpLevel = new JLabel(String.valueOf(this.getController().getLevel()));
        tmpLevel.setFont(font2);

        Border borderLevel = tmpScore.getBorder();
        tmpLevel.setBorder(new CompoundBorder(borderLevel, marginValues));

        this.setLevelValue(tmpLevel);


        // ajout des éléments dans la fenêtre
        infos.add(scoreLabel);
        infos.add(this.getScoreValue());

        infos.add(aliensLabel);
        infos.add(this.getAliensValue());

        infos.add(levelLabel);
        infos.add(this.getLevelValue());


        // redimension
        infos.setPreferredSize(new Dimension(width, height));

        infos.setVisible(true);

        return infos;
    }

    public void buildWindow() {
        // init du board (le jeu en lui même)
        this.setBoardPanel(new BoardPanel(this.getController(), this.getWidthBoard(), this.getHeightWindow()));
        this.getController().setBoard(this.getBoardPanel());

        // init des informations à droite de l'écran
        this.setInfoPanel(this.buildInfoPanel(this.getWidthInfo(), this.getHeightWindow()));

        // mise en place du menu de la fenêtre
        JMenuBar menu = this.buildMenu();
        this.setJMenuBar(menu);

        // on ajoute le jeu et les informations à la fenêtre
        this.add(this.getBoardPanel());
        this.add(this.getInfoPanel());

        // on aligne sur l'axe X les deux JFrame
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

        this.setVisible(true);
    }

    // run de la classe chargé de détecter les entrées claviers. Un tableau avec des booléens est chargé de retenir les touches appuyé. Cela permet de brancher n'importe quelle classe sur les entrées claviers.
    public void runKeyboard() {
        Keyboard keyboard = new Keyboard();
        this.addKeyListener(keyboard);
        this.getController().setKeyboard(keyboard);
    }

    // lors d'un update via le MVC, on met à jour les trois éléments d'informations à droite de la fenêtre principale
    public void update(Observable observable, Object o) {
        this.getScoreValue().setText(String.valueOf(this.getController().getScore()));
        this.getLevelValue().setText(String.valueOf(this.getController().getLevel()));
        this.getAliensValue().setText(String.valueOf(this.getController().getNbAlien()));
    }



    /*
     * GETTER & SETTER
     * */
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
