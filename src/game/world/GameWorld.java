package game.world;

import game.engine.GameLib;
import game.entities.*;
import game.utils.Vec2;
import game.systems.Drawable;

public class GameWorld implements Drawable {
  public static GameWorld world;
  public static long delta;
  public static long currentTime;

  public Player p;
	public EnemyManager em;
	public ProjectileManager pm;
  public BackgroundManager bm;

  public GameWorld() {
    world = this;
    currentTime = System.currentTimeMillis();

    this.p = new Player(new Vec2(GameLib.WIDTH/2, GameLib.HEIGHT*0.9), new Vec2(0.25, 0.25), 12.0);
    this.em = new EnemyManager();
    this.pm = new ProjectileManager();
    this.bm = new BackgroundManager();
  }

  public static void spawnPlayerProjectile(Vec2 pos, Vec2 vel, int size) {
    world.pm.spawnPlayerProjectile(pos, vel, size);
  }

  public void update() {
    /* Usada para atualizar o estado dos elementos do jogo    */
    /* (player, projéteis e inimigos) "delta" indica quantos  */
    /* ms se passaram desde a última atualização.             */
    delta = System.currentTimeMillis() - currentTime;
    
    /* Já a variável "currentTime" nos dá o timestamp atual.  */
    currentTime = System.currentTimeMillis();

    p.update(delta);
    em.update(delta);
    pm.update(delta);
    bm.update(delta);
  }

  public void draw() {
    bm.draw();
    p.draw();
    em.draw();
    pm.draw();
  }
}
