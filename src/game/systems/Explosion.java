package game.systems;

import game.engine.Drawable;
import game.engine.GameLib;
import game.utils.Vec2;
import game.world.GameWorld;

public class Explosion implements Drawable {
    public double start;
    public double end;
    private Vec2 pos;

    public void startExplosion(double dur, Vec2 pos) {
        this.start = GameWorld.currentTime;
        this.end = GameWorld.currentTime + dur;
        this.pos = new Vec2(pos);
    }

    public void draw() {
        double alpha = (GameWorld.currentTime - this.start) / (this.end - this.start);
        GameLib.drawExplosion(this.pos.x, this.pos.y, alpha);
    }
}