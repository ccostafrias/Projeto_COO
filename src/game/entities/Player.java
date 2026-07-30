package game.entities;

import java.awt.Color;

import game.engine.GameLib;
import game.systems.Explosion;
import game.utils.Vec2;
import game.world.GameWorld;

public class Player extends Entity {
  Explosion explosion = new Explosion();
  double nextShot = 0;
  double invulnerable = 0;

  public Player(Vec2 pos, Vec2 vel, double radius) {
    super(pos, vel, radius);
  }

  public void update(double dt) {
    this.updateMove(dt);
    this.updateShoot(dt);
    this.updateState(dt);
  }

  private void updateMove(double dt) {
    if (!this.isAlive()) return;

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
    if (this.isAlive() && GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
      
      if (GameWorld.currentTime > this.nextShot){
        GameWorld.spawnPlayerProjectile(new Vec2(this.pos.x, this.pos.y - 2*this.radius), new Vec2(0.0, -1.0), 2);
        this.nextShot = GameWorld.currentTime + 100;
      }	
    }
  }

  private void updateState(double dt) {
    /* Verificando se a explosão do player já acabou.         */
    /* Ao final da explosão, o player volta a ser controlável */

    switch (this.state) {
      case State.EXPLODING:
        if (GameWorld.currentTime > this.explosion.end){
          this.invulnerable = GameWorld.currentTime + 1000.0;
          this.state = State.INVULNERABLE;
        }

        break;
      case State.INVULNERABLE:
        if (GameWorld.currentTime > this.invulnerable) {
          this.state = State.ACTIVE;
        }
        break;

      default:
    }
  }

  public void hit() {
    this.state = State.EXPLODING;
    this.explosion.startExplosion(2000.0, this.pos);
  }

  public void draw() {
    switch (this.state) {
      case State.EXPLODING:
        this.explosion.draw();

        break;
      case State.INVULNERABLE:
        int k = 200;
        if (GameWorld.currentTime % k < k/2) return;

        GameLib.setColor(Color.WHITE);
        GameLib.drawPlayer(this.pos.x, this.pos.y, this.radius);

        break;

      case State.ACTIVE:
        GameLib.setColor(Color.WHITE);
        GameLib.drawPlayer(this.pos.x, this.pos.y, this.radius);

      default:
    }
  }
}