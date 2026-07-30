package game.entities;

import game.systems.Explosion;
import game.utils.Vec2;
import game.world.GameWorld;

public abstract class Enemy extends Entity {
  Explosion explosion = new Explosion();

  protected double speed;
  protected double angle;
  protected double rv;

  public Enemy(Vec2 pos, double speed, double angle, double radius) {
    super(pos, new Vec2(0, 0), radius); 
    this.speed = speed;
    this.angle = angle;
  }

  public void hit() {
    this.state = State.EXPLODING;
    explosion.startExplosion(500);
  }

  public void update(double dt) {
    if (this.isExploding()) {
      if (GameWorld.currentTime > explosion.end) {
        this.setState(State.INACTIVE);
      }
      return;
    }

    if (this.isActive()) {
      move(dt);
      shoot(); 
      checkBounds();
    }
  }

  protected abstract void move(double dt);
  protected abstract void shoot();
  protected abstract void checkBounds();  
}