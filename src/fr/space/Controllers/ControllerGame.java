package fr.space.Controllers;

import fr.space.Models.*;
import fr.space.Utils.GameState;
import fr.space.Views.BoardPanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControllerGame extends Controller  implements Runnable {

    private boolean runningUpdate = false;


    /*
    * Constructors
    * */
    public ControllerGame(GameModel gameModel) {
        super(gameModel);
    }



    /*
    * Methods
    * */
    // boucle principale du projet, elle va lancer toutes les méthodes d'update et de rendu à timing prédéfini
    public void run() {
        this.setRunningUpdate(true); // boucle de jeu à vrai

        long initialTime = System.nanoTime(); // temps au démarrage

        final double target_UPS = 128, target_FPS = 200; // on vise les 128 UPS pour 200 fps

        final double timeU = 1000000000 / target_UPS;
        final double timeF = 1000000000 / target_FPS;

        double deltaU = 0, deltaF = 0; // init des deltas qui serviront à équilibrer la vitesse du jeu
        int frames = 0, ticks = 0; // init des ticks et frames par secondes

        long timer = System.currentTimeMillis(); // maintenant

        while(this.isRunningUpdate()) { // on boucle tant que la valeur est à vrai

            long currentTime = System.nanoTime();

            // calcul des deltas
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            // si on a besoin d'update pour atteindre l'objectif target_UPS alors on lance l'update
            if(deltaU >= 1) {
                this.update(deltaU);

                ticks++;
                deltaU--;
            }

            // si on a besoin de rendre pour atteindre l'objectif target_FPS alors on lance le rendu
            if(deltaF >= 1) {
                this.getGameModel().render();

                frames++;
                deltaF--;
            }


            // reset toutes les secondes du nombre de UPS et de FPS par rapport au travail réalisé
            if(System.currentTimeMillis() - timer > 1000) {
                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));

                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }

    }

    // boucle update principal du projet
    public void update(double delta) {

        this.getInput(delta); // on run la méthode charger récupère les entrées clavier dans la classe Keyboard et d'exécuter les actions

        if(!this.getGameModel().isPause() && !this.getGameModel().isLose()) { // si le jeu n'est ni en pause ni perdu

            this.spawnEnemies(); // on va vérifier que il y a bien une vague déjà en jeu, si ce n'est pas le cas, on spawn une wave

            this.getGameModel().getPlayer().update(delta); // méthode de mise à jour du joueur




            // update explosions, on supprime l'explosion si elle n'a plus de vie
            Iterator<Explosion> iterExplosions = this.getGameModel().getExplosions().iterator();
            while (iterExplosions.hasNext()) {
                Explosion e = iterExplosions.next();

                e.update(delta);

                if (e.getLife() <= 0) {
                    iterExplosions.remove();
                }
            }


            // update bullet, on supprime la bullet si elle n'a plus de vie
            Iterator<Bullet> iterBullets = this.getGameModel().getBullets().iterator();
            while (iterBullets.hasNext()) {
                Bullet b = iterBullets.next();

                b.update(delta);

                if (b.getLife() <= 0) {
                    iterBullets.remove();
                }
            }


            this.getGameModel().getWave().update(delta); // update de la wave en entier

            this.collisionDetection(); // lancement de la boucle de vérification des collisions

            this.calculateIfLoose(); // lancement de la méthode qui vérifie si la game est perdu
        }
    }

    // traitement des entrées depuis la classe KeyBoard
    public void getInput(double delta) {
        if(!this.getGameModel().isPause() && !this.getGameModel().isLose()) {
            if (this.getGameModel().getKeyboard().getKey(32)) // shoot (espace)
                this.getGameModel().getPlayer().shoot();

            if (this.getGameModel().getKeyboard().getKey(37)) // left
                this.getGameModel().getPlayer().move(delta, -1, 0);

            if (this.getGameModel().getKeyboard().getKey(39)) // right
                this.getGameModel().getPlayer().move(delta, 1, 0);
        }

        // détection de la pause
        if (!this.getGameModel().isLose() && this.getGameModel().getKeyboard().getKey(80)) { // pause (P)
            this.getGameModel().turnPause();


            // fix de faite que le joueur rester appuyé sur sa touche P un petit peu trop longtemps sans le vouloir
            this.getGameModel().getKeyboard().setKey(80, false);
        }

        // restart d'une game après une défaite
        if(this.getGameModel().isLose()) {
            if(this.getGameModel().getKeyboard().getKey(82)) { // start game after a game over (R)
                this.getGameModel().reset();
            }
        }
    }

    // détection de la non présence d'énnemis et respawn
    public void spawnEnemies() {
        if(this.getGameModel().getWave().getAliens().size() == 0) { // si aucun ennemis dans la wave
            // default enemy's wave
            Alien alien = new Alien(null, 1, Sprite.getSpriteAlien());
            alien.setSpeed(1);

            // on créer une copie de la wave par défaut pour l'appliquer
            Wave waveToSet = new Wave(this.getGameModel().getDefaultWave());
            this.getGameModel().setWave(waveToSet);

            // spawn des aliens de la wave
            this.getGameModel().getWave().spawn();

            // passage au niveau supérieur
            this.getGameModel().setLevel(this.getLevel() + 1);
        }
    }

    // vérifie si le la partie est perdu ou non
    private void calculateIfLoose() {
        // si le joueur est mort alors la game est perdu
        if(this.getGameModel().getPlayer().getLife() <= 0) {
            this.getGameModel().setGameState(GameState.LOSE);
        } else { // si un alien a atteint le bas de l'écran alors la game est perdu

            for (Alien e: this.getGameModel().getWave().getAliens()) {
                // calcul par rapport à la position des aliens si ils ont atteint la limite inférieure de l'écran ou non
                int yValueForLoose = this.getGameModel().getySize() - e.getSprite().getyDimension();
                if(e.getPosition().getY() - this.getGameModel().getMenuSize() >= yValueForLoose) {
                    this.getGameModel().setGameState(GameState.LOSE);
                }
            }

        }

    }

    // détection collision, si il y a une collision, on retire de la vie aux deux entités
    public void collisionDetection() {
        for (Entity e1: this.getEntities()) {
            for (Entity e2: this.getEntities()) {
                if (e1.isHitbox() && e2.isHitbox() && e1.collisionWith(e2)) {
                    e1.removeLife(1);
                    e2.removeLife(1);
                }
            }
        }
    }

    // on applique la configuration de l'interval de tire
    public void applyConfigIntervalShoot(long interval) {
        Spaceship.setShootInterval(interval);
    }

    // on applique la configuration du nombre de ligne et de colonnes
    public void applyConfigNumberAliens(int row, int col) {
        Wave wave = this.getGameModel().getDefaultWave();

        wave.setRow(row);
        wave.setCol(col);
    }

    // on applique la configuration de la vitesse des aliens
    public void applyConfigSpeed(float speed) {
        this.getGameModel().getWave().setSpeedToAllAliens(speed);
    }

    // on récupère l'ensemble des entités
    public List<Entity> getEntities() {
        List<Entity> entities = new ArrayList<Entity>();

        entities.addAll(this.getGameModel().getExplosions());
        entities.addAll(this.getGameModel().getWave().getAliens());
        entities.addAll(this.getGameModel().getBullets());
        entities.add(this.getGameModel().getPlayer());

        return entities;
    }



    /*
     * GETTER & SETTER
     * */
    public void setBoard(BoardPanel boardPanel) {
        this.getGameModel().setBoardPanel(boardPanel);
    }


    public BoardPanel getBoardPanel() { return this.getGameModel().getBoardPanel(); }


    public void setKeyboard(Keyboard keyboard) {
        this.getGameModel().setKeyboard(keyboard);
    }


    public int getScore() {
        return this.getGameModel().getScore();
    }


    public int getLevel() {
        return this.getGameModel().getLevel();
    }


    public int getNbAlien() {
        return this.getGameModel().getNbAlien();
    }


    public long getShootInterval() {
        return this.getGameModel().getPlayer().getShootInterval();
    }


    public float getAliensSpeed() {
        return this.getGameModel().getWave().getAlien().getSpeed();
    }


    public int getAliensRow() {
        return this.getGameModel().getWave().getRow();
    }


    public int getAliensCol() {
        return this.getGameModel().getWave().getCol();
    }


    public boolean isRunningUpdate() {
        return runningUpdate;
    }

    public void setRunningUpdate(boolean runningUpdate) {
        this.runningUpdate = runningUpdate;
    }
}
