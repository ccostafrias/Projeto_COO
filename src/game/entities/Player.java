package game.entities;

import java.awt.Color;
import game.engine.GameLib;
import game.systems.Explosion;
import game.utils.Vec2;
import game.world.GameWorld;

public class Player extends Entity {
  Explosion explosion = new Explosion();
  double nextShot = 0;

  public Player(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void update(double dt) {
    this.updateMove(dt);
    this.updateShoot(dt);
    this.updateState(dt);
  }

  private void updateMove(double dt) {
    if (this.state != State.ACTIVE) return;

    if (GameLib.iskeyPressed(GameLib.KEY_UP)) this.pos.y -= dt * this.vel.y;
    if (GameLib.iskeyPressed(GameLib.KEY_DOWN)) this.pos.y += dt * this.vel.y;
    if (GameLib.iskeyPressed(GameLib.KEY_LEFT)) this.pos.x -= dt * this.vel.x;
    if (GameLib.iskeyPressed(GameLib.KEY_RIGHT)) this.pos.x += dt * this.vel.x;

    /* Verificando se coordenadas do player ainda estão dentro */
    /* da tela de jogo após processar entrada do usuário.      */
			
    if (this.pos.x < 0.0) this.pos.x = 0.0;
    if (this.pos.x >= GameLib.WIDTH) this.pos.x = GameLib.WIDTH - 1;
    if (this.pos.y < 25.0) this.pos.y = 25.0;
    if (this.pos.y >= GameLib.HEIGHT) this.pos.y = GameLib.HEIGHT - 1;
  }

  private void updateShoot(double dt) {
    if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
      
      if (GameWorld.currentTime > this.nextShot){
        GameWorld.spawnPlayerProjectile(new Vec2(this.pos.x, this.pos.y - 2*this.radius), new Vec2(0.0, -1.0), 2);
        this.nextShot = GameWorld.currentTime + 100;
      }	
    }
  }

  private void updateState(double dt) {
    /* Verificando se a explosão do player já acabou.         */
    /* Ao final da explosão, o player volta a ser controlável */
    if (this.state == State.EXPLODING){
      
      if(GameWorld.currentTime > this.explosion.end){
        this.state = State.ACTIVE;
      }
    }
  }

  public void hit() {
    this.state = State.EXPLODING;
    this.explosion.startExplosion(2000.0);
  }

  public void draw() {
    if (this.state == State.EXPLODING){
      
      double alpha = (GameWorld.currentTime - this.explosion.start) / (this.explosion.end - this.explosion.start);
      GameLib.drawExplosion(this.pos.x, this.pos.y, alpha);
    } else {
      GameLib.setColor(Color.BLUE);
      GameLib.drawPlayer(this.pos.x, this.pos.y, this.radius);
    }
  }
}