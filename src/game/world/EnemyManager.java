package game.world;

import java.util.*;
import game.entities.*;
import game.systems.*;

public class EnemyManager implements Updatable, Drawable {
  private List<Enemy> enemies;

  public EnemyManager(){
    this.enemies = new ArrayList<>();
  }

  public void add(Enemy e) {
    enemies.add(e);
  }

  public void update(double dt) {
    Iterator<Enemy> it = enemies.iterator();

    while (it.hasNext()) {
      Enemy e = it.next();
      e.update(dt);
    }
  }

  public void draw() {
    Iterator<Enemy> it = enemies.iterator();

    while (it.hasNext()) {
      Enemy e = it.next();
      e.draw();
    }
  }
}