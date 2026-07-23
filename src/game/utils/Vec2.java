package game.utils;

public class Vec2 {
  public double x;
  public double y;

  public Vec2()  {
    this(0, 0);
  }

  public Vec2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vec2(Vec2 other) {
    this.x = other.x;
    this.y = other.y;
  }

  public Vec2 set(Vec2 other) {
    this.x = other.x;
    this.y = other.y;
    return this;
  }

  public Vec2 add(Vec2 other) {
    return new Vec2(this.x + other.x, this.y + other.y);
  }

  public Vec2 addLocal(Vec2 other) {
    this.x += other.x;
    this.y += other.y;
    return this; 
  }

  public Vec2 sub(Vec2 other) {
    return new Vec2(this.x - other.x, this.y - other.y);
  }

  public Vec2 subLocal(Vec2 other) {
    this.x -= other.x;
    this.y -= other.y;
    return this; 
  }

  public Vec2 scale(double scalar) {
    return new Vec2(this.x * scalar, this.y * scalar);
  }

  public Vec2 scaleLocal(double scalar) {
    this.x *= scalar;
    this.y *= scalar;
    return this; 
  }

  public Vec2 normalize() {
    double norma = Math.sqrt(this.x*this.x + this.y*this.y);
    if (norma == 0) return new Vec2();

    return new Vec2(this.x, this.y).scaleLocal(norma);
  }

  public Vec2 normalizeLocal() {
    double norma = Math.sqrt(this.x*this.x + this.y*this.y);
    if (norma == 0) return this;

    return this.scaleLocal(norma);
  }

  static public double dist(Vec2 a, Vec2 b) {
    double dx = a.x - b.x;
    double dy = a.y - b.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
  
}