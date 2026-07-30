package game.entities;

import game.utils.Vec2;
import game.engine.GameLib;
import game.world.GameWorld; 
import java.awt.Color;

public class EnemyCircle extends Enemy {
    private double nextShoot; 

    public EnemyCircle(Vec2 pos, double speed, double angle) {
        super(pos, speed, angle, 9.0);
        this.rv = 0.0;
        this.nextShoot = GameWorld.currentTime + 500;
    }

    @Override
    protected void move(double dt) {
        this.pos.x += this.speed * Math.cos(this.angle) * dt;
        this.pos.y += this.speed * Math.sin(this.angle) * dt;
        this.angle += this.rv * dt;
    }

    @Override
    protected void shoot() {
        if (GameWorld.currentTime > this.nextShoot) {
            GameWorld.spawnEnemyProjectile(new Vec2(this.pos), Vec2.polarToVec(this.angle, 0.45), 2);
            this.nextShoot = (long) GameWorld.currentTime + 200 + Math.random() * 500;
        }
    }

    @Override
    protected void checkBounds() {
        if (this.pos.y > GameLib.HEIGHT + 10) {
            this.setState(State.INACTIVE);
        }
    }

    @Override
    public void draw() {
        if (this.isExploding()) {
            this.explosion.draw();
        } else if (this.isActive()) {
            GameLib.setColor(Color.CYAN);
            GameLib.drawCircle(this.pos.x, this.pos.y, this.radius);
        }
    }
}
