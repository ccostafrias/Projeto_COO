package game.systems;

import game.world.GameWorld;

public class Explosion {
    public double start;
    public double end;

    public void startExplosion(double dur) {
        this.start = GameWorld.currentTime;
        this.end = GameWorld.currentTime + dur;
    }
}