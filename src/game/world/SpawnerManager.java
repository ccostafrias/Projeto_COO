package game.world;

import java.util.*;

import game.engine.GameLib;
import game.engine.Updatable;
import game.entities.*;
import game.utils.Vec2;

public class SpawnerManager implements Updatable {
  List<EnemySpawner> spawners;

  public SpawnerManager() {
    this.spawners = new ArrayList<>();

    this.spawners.add(new EnemyCircleSpawner(2000));
    this.spawners.add(new EnemyWormSpawner(7000));
    this.spawners.add(new EnemySnakeSpawner(3000));
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
  protected double nextEnemy;

  public EnemySpawner(int delay) {
    this.nextEnemy = GameWorld.currentTime + delay;
  }

  public boolean canSpawn() {
    return GameWorld.currentTime > nextEnemy;
  }

  abstract public void spawnEnemy();
}

class EnemyCircleSpawner extends EnemySpawner {
  public EnemyCircleSpawner(int delay) {
    super(delay);
  }

  public void spawnEnemy() {
    Enemy e = new EnemyCircle(new Vec2(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0), 0.20 + Math.random() * 0.15, -(3 * Math.PI) / 2);
    GameWorld.spawnEnemy(e);

    nextEnemy = GameWorld.currentTime + 500;
  }
}

class EnemyWormSpawner extends EnemySpawner {
  int count = 0;
  double spawnX = GameLib.WIDTH * 0.20;

  public EnemyWormSpawner(int delay) {
    super(delay);
  }

  public void spawnEnemy() {
    Enemy e = new EnemyWorm(new Vec2(spawnX, -10.0), 0.3, -(3 * Math.PI) / 2);
    GameWorld.spawnEnemy(e);
    this.count++;

    if (this.count < 10) {
      this.nextEnemy = GameWorld.currentTime + 120;
    } else {
      this.count = 0;
      this.spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
      this.nextEnemy = (long) (GameWorld.currentTime + 4000 + Math.random() * 2000);
    }

  }
}

class EnemySnakeSpawner extends EnemySpawner {
  private int count = 0;
  private int toSpawn;
  private double spawnX;

  public EnemySnakeSpawner(int delay) {
    super(delay);
    setRandomX();
    setToSpawn();
  }

  public void spawnEnemy() {
    EnemySnake.Part part;

    if (this.count == 0) {
      part = EnemySnake.Part.HEAD;
    } else if (this.count == this.toSpawn - 1) {
      part = EnemySnake.Part.TAIL;
    } else {
      part = EnemySnake.Part.BODY;
    }

    Enemy e = new EnemySnake(new Vec2(spawnX, -10), 0.3, -(3 * Math.PI) / 2, part);
    GameWorld.spawnEnemy(e);
    this.count++;

    if (this.count < this.toSpawn) {
      this.nextEnemy = GameWorld.currentTime + 75;
    } else {
      this.count = 0;
      setRandomX();
      setToSpawn();
      this.nextEnemy = (long) (GameWorld.currentTime + 5000 + Math.random() * 2000);
    }
  }

  private void setToSpawn() {
    toSpawn = 4 + (int) (Math.random()*6);
  }

  private void setRandomX() {
    spawnX =  GameLib.WIDTH * 0.2 + Math.random() * GameLib.WIDTH * 0.6;
  }
}