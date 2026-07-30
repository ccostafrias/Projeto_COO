package game.world;

import game.engine.GameLib;
import game.entities.*;
import game.systems.Drawable;
import game.utils.Vec2;

public class GameWorld implements Drawable {
  public static GameWorld world;
  public static long delta;
  public static long currentTime;

  public Player p;
  public EnemyManager em;
  public ProjectileManager pm;
  public BackgroundManager bm;
  public CollisionManager cm;
  public SpawnerManager sm;

  public GameWorld() {
    world = this;
    currentTime = System.currentTimeMillis();

    this.p = new Player(new Vec2(GameLib.WIDTH/2, GameLib.HEIGHT*0.9), new Vec2(0.25, 0.25), 12.0);
    this.em = new EnemyManager();
    this.pm = new ProjectileManager();
    this.bm = new BackgroundManager();
    this.cm = new CollisionManager();
    this.sm = new SpawnerManager();
  }

  public static void spawnPlayerProjectile(Vec2 pos, Vec2 vel, int size) {
    world.pm.spawnPlayerProjectile(pos, vel, size);
  }
  
  public static void spawnEnemyProjectile(Vec2 pos, Vec2 vel, int size) {
    world.pm.spawnEnemyProjectile(pos, vel, size);
  }
  
  public static void spawnEnemy(Enemy e) {
    world.em.add(e);
  }

  public void update() {
    /* Usada para atualizar o estado dos elementos do jogo    */
    /* (player, projéteis e inimigos) "delta" indica quantos  */
    /* ms se passaram desde a última atualização.             */
    delta = System.currentTimeMillis() - currentTime;
    
    /* Já a variável "currentTime" nos dá o timestamp atual.  */
    currentTime = System.currentTimeMillis();

    sm.update(delta);
    cm.update(delta);
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
