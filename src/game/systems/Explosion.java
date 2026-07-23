package game.systems;

import game.world.GameWorld;

public class Explosion {
    public double start = 0;
    public double end = 0;

    public Explosion(double dur) {
        this.start = GameWorld.currentTime;
        this.end = GameWorld.currentTime + dur;
    }
}