package game.entities;

import game.utils.Vec2;
import game.systems.Drawable;
import game.systems.Updatable;

public abstract class Entity implements Drawable, Updatable {
  public enum State {
    INACTIVE,
    ACTIVE,
    EXPLODING
  }

  protected Vec2 pos;
  protected Vec2 vel;

  protected double radius;
  protected State state = State.ACTIVE;

  public Entity(Vec2 pos, Vec2 vel, double radius) {
    this.pos = new Vec2(pos);
    this.vel = new Vec2(vel);
    this.radius = radius;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

  public abstract void update(double dt);
  public abstract void draw();
}