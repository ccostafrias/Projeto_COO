package game.world;

import java.util.*;

import game.engine.GameLib;
import game.entities.*;
import game.systems.Updatable;
import game.utils.Vec2;

public class SpawnerManager implements Updatable {
  List<EnemySpawner> spawners;

  public SpawnerManager() {
    this.spawners = new ArrayList<>();

    this.spawners.add(new EnemyCircleSpawner());
    this.spawners.add(new EnemyWormSpawner());
  }

  public void update(double dt) {
    Iterator<EnemySpawner> it = spawners.iterator();

    while (it.hasNext()) {
      EnemySpawner es = it.next();

      if (es.canSpawn()) {
        es.spawnEnemy();
      }
    }
  }
}

abstract class EnemySpawner {
  protected double nextEnemy = 0;

  public boolean canSpawn() {
    return GameWorld.currentTime > nextEnemy;
  }

  abstract public void spawnEnemy();
}

class EnemyCircleSpawner extends EnemySpawner {
  public void spawnEnemy() {
    Enemy e = new EnemyCircle(new Vec2(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0), 0.20 + Math.random() * 0.15, (3 * Math.PI) / 2);
    GameWorld.spawnEnemy(e);

    nextEnemy = GameWorld.currentTime + 500;
  }
}

class EnemyWormSpawner extends EnemySpawner {
  int count = 0;
  double spawnX = GameLib.WIDTH * 0.20;

  public void spawnEnemy() {
    Enemy e = new EnemyWorm(new Vec2(spawnX, -10.0), 0.42, (3 * Math.PI) / 2);
    GameWorld.spawnEnemy(e);
    this.count++;

    if (this.count < 10) {
      this.nextEnemy = GameWorld.currentTime + 120;
    } else {
      this.count = 0;
      this.spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
      this.nextEnemy = (long) (GameWorld.currentTime + 3000 + Math.random() * 3000);
    }

  }
}