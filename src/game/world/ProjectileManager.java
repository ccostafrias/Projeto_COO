package game.world;

import java.util.*;
import game.entities.*;
import game.entities.Entity.State;
import game.systems.*;
import game.utils.Vec2;

public class ProjectileManager implements Updatable, Drawable {
  private ProjectileList playerProjectiles;
  private ProjectileList enemiesProjectiles;

  public ProjectileManager(){
    this.playerProjectiles = new ProjectileList();
    this.enemiesProjectiles = new ProjectileList();
  }

  public void spawnPlayerProjectile(Vec2 pos, Vec2 vel, int size) {
    this.playerProjectiles.add((new PlayerProjectile(pos, vel, size)));
  }

  public void update(double dt) {
    this.playerProjectiles.update(dt);
    this.enemiesProjectiles.update(dt);
  }

  public void draw() {
    this.playerProjectiles.draw();
    this.enemiesProjectiles.draw();
  }
}

class ProjectileList implements Updatable, Drawable {
  private HashSet<Projectile> projectiles;

  public ProjectileList(){
    this.projectiles = new HashSet<>();
  }

  public void add(Projectile p) {
    projectiles.add(p);
  }

  public void update(double dt) {
    Iterator<Projectile> it = projectiles.iterator();

    while (it.hasNext()) {
      Projectile p = it.next();

      p.update(dt);
      System.out.println(this.projectiles.size());

      if (p.getState() == State.INACTIVE) {
        it.remove();
      }
    }
  }

  public void draw() {
    Iterator<Projectile> it = projectiles.iterator();

    while (it.hasNext()) {
      Projectile p = it.next();
      p.draw();
    }
  }
}