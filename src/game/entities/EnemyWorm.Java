package game.entities;

import game.utils.Vec2;
import game.engine.GameLib;
import game.world.GameWorld; 
import java.awt.Color;

public class EnemyWorm extends Enemy {

    public EnemyWorm(Vec2 pos, double speed, double angle) {
        super(pos, speed, angle, 12.0);
        this.rv = 0.0;
    }

    @Override
    protected void move(double dt) {
        double previousY = this.pos.y;
        
        this.pos.x += this.speed * Math.cos(this.angle) * dt;
        this.pos.y += this.speed * Math.sin(this.angle) * dt * (-1.0);
        this.angle += this.rv * dt;

        double threshold = GameLib.HEIGHT * 0.30;

        if (previousY < threshold && this.pos.y >= threshold) {
            if (this.pos.x < GameLib.WIDTH / 2) this.rv = 0.003;
            else this.rv = -0.003;
        }
    }

    @Override
    protected void shoot() {
        boolean shootNow = false;

        if (this.rv > 0 && Math.abs(this.angle - 3 * Math.PI) < 0.05) {
            this.rv = 0.0;
            this.angle = 3 * Math.PI;
            shootNow = true;
        }
        
        if (this.rv < 0 && Math.abs(this.angle) < 0.05) {
            this.rv = 0.0;
            this.angle = 0.0;
            shootNow = true;
        }

        if (shootNow) {
            double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };

            for (int k = 0; k < angles.length; k++) {
                
                double a = angles[k] + Math.random() * Math.PI/6 - Math.PI/12;
                double vx = Math.cos(a);
                double vy = Math.sin(a);
    
                GameWorld.spawnEnemyProjectile(new Vec2(this.pos), new Vec2(vx, vy).scaleLocal(0.3), 2);
            }

        }
    }

    @Override
    protected void checkBounds() {
        if (this.pos.x < -10 || this.pos.x > GameLib.WIDTH + 10) {
            this.setState(State.INACTIVE);
        }
    }

    @Override
    public void draw() {
        if (this.isExploding()) {
            double alpha = (GameWorld.currentTime - explosion.start) / (explosion.end - explosion.start);
            GameLib.drawExplosion(this.pos.x, this.pos.y, alpha);
        } else if (this.isActive()) {
            GameLib.setColor(Color.MAGENTA);
            GameLib.drawDiamond(this.pos.x, this.pos.y, this.radius);
        }
    }
}