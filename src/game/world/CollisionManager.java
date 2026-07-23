package game.world;

import java.util.*;

import game.entities.Entity;
import game.entities.Projectile;
import game.entities.Enemy;
import game.systems.Updatable;
import game.utils.Vec2;

/***************************/
/* Verificação de colisões */
/***************************/
public class CollisionManager implements Updatable {
  
  private boolean checkCollision(Entity a, Entity b) {
    double dist = Vec2.dist(a.getPos(), b.getPos());

    return dist < (a.getRadius() + b.getRadius()) * 0.8;
  }

  /* colisões player - inimigos */
  private void checkPlayerEnemyCollision() {
    if (GameWorld.world.p.isActive()){
      
      Iterator<Enemy> it = GameWorld.world.em.iterator();

      while (it.hasNext()) {
        Enemy e = it.next();

        if (checkCollision(GameWorld.world.p, e)) {
          e.hit();
        }
      }    
    }
  }

  /* colisões player - projeteis (inimigo) */
  private void checkPlayerEnemyProjectilesCollision() {
    Iterator<Projectile> it = GameWorld.world.pm.enemyIterator();

    while (it.hasNext()) {
      Projectile projectile = it.next();

      if (checkCollision(GameWorld.world.p, projectile)) {
        GameWorld.world.p.hit();
      }
    }
  }

  /* colisões inimigo - projeteis (player) */
  private void checkPlayerProjectilesEnemyCollision() {
    Iterator<Projectile> p_it = GameWorld.world.pm.playerIterator();

    while (p_it.hasNext()) {
      Projectile projectile = p_it.next();

      Iterator<Enemy> e_it = GameWorld.world.em.iterator();

      while (e_it.hasNext()) {
        Enemy enemy = e_it.next();

        if (checkCollision(enemy, projectile)) {
          enemy.hit();
        }
      }

    }
  }

  public void update(double dt) {
    checkPlayerEnemyCollision();
    checkPlayerEnemyProjectilesCollision();
    checkPlayerProjectilesEnemyCollision();
  }
}
