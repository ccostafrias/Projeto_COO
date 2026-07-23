package game.entities;

import java.awt.Color;

import game.engine.GameLib;
import game.utils.Vec2;

public class EnemyProjectile extends Projectile {
  public EnemyProjectile(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void update(double dt) {

  }

  public void draw() {
    if (this.state == State.ACTIVE){
      GameLib.setColor(Color.RED);
      GameLib.drawCircle(this.pos.x, this.pos.y, this.radius);
    }
  }
}