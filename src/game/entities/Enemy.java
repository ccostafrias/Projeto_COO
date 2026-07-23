package game.entities;

import game.systems.Explosion;
import game.utils.Vec2;

public class Enemy extends Entity {
  Explosion explosion = new Explosion();

  public Enemy(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void hit() {
    this.state = State.EXPLODING;
    explosion.startExplosion(500);
  }

  public void update(double dt) {

  }

  public void draw() {
    
  }
}