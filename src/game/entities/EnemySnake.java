package game.entities;

import game.utils.Vec2;
import game.engine.GameLib;
import game.world.GameWorld; 
import java.awt.Color;

public class EnemySnake extends Enemy {
    private double nextShoot; 
    private double birthTime;

    public enum Part {
        HEAD,
        BODY,
        TAIL
    }

    private Part part;

    public EnemySnake(Vec2 pos, double speed, double angle, Part part) {
        super(pos, speed, angle, 12.0);
        this.birthTime = GameWorld.currentTime;
        this.part = part;
        this.nextShoot = GameWorld.currentTime + 200 + Math.random()*600;
    }

    @Override
    protected void move(double dt) {
        double t = (GameWorld.currentTime - this.birthTime) / 1000.0;

        double k = 2;
        double amplitude = 0.1;

        double vx = amplitude * k * Math.cos(k * t);
        double vy = this.speed;

        this.pos.x += vx * dt;
        this.pos.y += vy * dt;

        this.angle = Math.atan2(vy, vx);
    }

    @Override
    protected void shoot() {
        if (GameWorld.currentTime > this.nextShoot) {
            GameWorld.spawnEnemyProjectile(new Vec2(this.pos.x + 15, this.pos.y), Vec2.polarToVec(0, 0.2), 2);
            GameWorld.spawnEnemyProjectile(new Vec2(this.pos.x - 15, this.pos.y), Vec2.polarToVec(Math.PI, 0.2), 2);
            this.nextShoot = (long) GameWorld.currentTime + 600 + Math.random()*200;
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
            GameLib.setColor(Color.ORANGE);
            switch (this.part) {
                case Part.HEAD:
                    GameLib.drawTriangle(this.pos.x, this.pos.y, this.radius, this.angle);
                    
                    break;
            
                case Part.BODY:
                    GameLib.drawRect(this.pos.x, this.pos.y, this.radius+5, this.radius+5, this.angle);

                    break;
                case Part.TAIL:
                    GameLib.drawTriangle(this.pos.x, this.pos.y, this.radius, this.angle + Math.PI);

                    break;
            }
        }
    }
}
