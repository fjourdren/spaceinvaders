package fr.space.Models;

public class Spaceship extends Entity {

    private GameModel gameModel;

    private static long shootInterval = 500000000; //0.5s in nanosecond
    private long lastShootTime;


    /*
     * Constructors
     * */
    public Spaceship(GameModel gameModel, Position position, int life, Sprite sprite) {
        super(position, life, sprite);

        this.setGameModel(gameModel);
        this.setLastShootTime(System.nanoTime());
    }



    /*
     * Methods
     * */
    public void update(double delta) {
        
    }

    public void shoot() {
        long now = System.nanoTime();

        if (now - this.getLastShootTime() > Spaceship.getShootInterval()) {

            Position bulletPosition = new Position((this.getPosition().getX() + (this.getSprite().getxDimension() / 2)) - Sprite.getSpriteBullet().getxDimension() / 2, this.getPosition().getY());

            this.setLastShootTime(System.nanoTime());

            Bullet bulletToAdd = new Bullet(this.getGameModel(), bulletPosition, 1, Sprite.getSpriteBullet(), this);
            this.getGameModel().addBullet(bulletToAdd);
        }
    }

    public void move(double delta, double xAbsice, double yAbsice) {
        int newX = (int) (this.getPosition().getX() + xAbsice * this.getSpeed() * delta);

        if(newX >= 0 && newX <= this.getGameModel().getxSize() - this.getSprite().getxDimension())
            super.move(delta, xAbsice, yAbsice);
    }



    /*
     * GETTER & SETTER
     * */
    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public static long getShootInterval() {
        return shootInterval;
    }

    public static void setShootInterval(long shootInterval) {
        Spaceship.shootInterval = shootInterval;
    }

    public long getLastShootTime() {
        return lastShootTime;
    }

    public void setLastShootTime(long lastShootTime) {
        this.lastShootTime = lastShootTime;
    }
}
