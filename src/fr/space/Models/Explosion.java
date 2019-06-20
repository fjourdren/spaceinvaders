package fr.space.Models;

public class Explosion extends Entity {

    private long spawnTime;
    private static long timeToLive = 200000000; // 0.2s


    /*
     * Constructors
     * */
    public Explosion(Position pos) {
        super(pos, 1, Sprite.getSpriteExplosion());

        this.setSpawnTime(System.nanoTime());
        this.setHitbox(false);
    }



    /*
     * Methods
     * */
    public void update(double delta) {
        long now = System.nanoTime();

        if(now > this.getSpawnTime() + Explosion.getTimeToLive()) {
            this.setLife(0);
        }
    }

    @Override
    public void move(double delta, double xAbsice, double yAbsice) {

    }



    /*
     * GETTER & SETTER
     * */
    public long getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(long spawnTime) {
        this.spawnTime = spawnTime;
    }

    public static long getTimeToLive() {
        return timeToLive;
    }

    public static void setTimeToLive(long timeToLive) {
        Explosion.timeToLive = timeToLive;
    }
}
