package game.entities;

import java.awt.Color;

import game.engine.GameLib;
import game.utils.Vec2;

public class PlayerProjectile extends Projectile {
  public PlayerProjectile(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void draw() {
    if (this.state == State.ACTIVE){
      
      GameLib.setColor(Color.GREEN);
      GameLib.drawLine(this.pos.x, this.pos.y - 5, this.pos.x, this.pos.y + 5);
      GameLib.drawLine(this.pos.x - 1, this.pos.y - 3, this.pos.x - 1, this.pos.y + 3);
      GameLib.drawLine(this.pos.x + 1, this.pos.y - 3, this.pos.x + 1, this.pos.y + 3);
    }
  }
}