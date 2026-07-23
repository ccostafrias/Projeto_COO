package game.entities;

import game.engine.GameLib;
import game.utils.Vec2;

public abstract class Projectile extends Entity {
  public Projectile(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void update(double dt) {
    if (this.state == State.ACTIVE){
        
      /* verificando se projétil saiu da tela */
      if(this.pos.y  < 0 || this.pos.y > GameLib.HEIGHT) {
        
        this.state = State.INACTIVE;
      } else {
      
        this.pos.x += this.vel.x * dt;
        this.pos.y += this.vel.y * dt;
      }
    }
  }

  abstract public void draw();
}