package game.world;

import java.util.*;
import java.awt.Color;

import game.engine.GameLib;
import game.systems.Drawable;
import game.systems.Updatable;
import game.utils.Vec2;

public class BackgroundManager implements Drawable, Updatable {
  StarList front;
  StarList back;

  public BackgroundManager() {
    this.front = new StarList(0.070, 20, 2);
    this.back = new StarList(0.045, 50, 3);
  }

  public void update(double dt) {
    front.update(dt);
    back.update(dt);
  }

  public void draw() {
    GameLib.setColor(Color.DARK_GRAY);
    front.draw();
    back.draw();
  }
}

class StarList implements Drawable, Updatable {
  List<Star> stars;
  protected double speed;
  protected double count = 0;

  public StarList(double speed, int quantity, int size) {
    this.speed = speed;
    this.stars = new ArrayList<>();

    for (int i = 0; i < quantity; i++) {
      stars.add(new Star(size, new Vec2(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT), this));
    }
  }

  public void update(double dt) {
    this.count += this.speed * dt;
  }

  public void draw() {
    Iterator<Star> it = stars.iterator();

    while (it.hasNext()) {
      Star s = it.next();
      s.draw();
    }
  }
}


class Star implements Drawable {
  private int size;
  private Vec2 pos;
  private StarList stars;

  public Star(int size, Vec2 pos, StarList stars) {
    this.size = size;
    this.pos = pos;
    this.stars = stars;
  }
  
  public void draw() {
		GameLib.fillRect(this.pos.x, (this.pos.y + this.stars.count) % GameLib.HEIGHT, size, size);
  }
}