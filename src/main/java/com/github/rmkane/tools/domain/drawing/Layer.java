package com.github.rmkane.tools.domain.drawing;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Layer {
  private String name;
  private Point2D offset;

  public Layer() {}

  public Layer(String name, Point2D offset) {
    this.name = name;
    this.offset = offset;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Point2D getOffset() {
    return this.offset;
  }

  public void setOffset(Point2D offset) {
    this.offset = offset;
  }

  public Layer name(String name) {
    setName(name);
    return this;
  }

  public Layer offset(Point2D offset) {
    setOffset(offset);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Layer)) {
      return false;
    }
    Layer layer = (Layer) o;
    return Objects.equals(name, layer.name) && Objects.equals(offset, layer.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, offset);
  }

  @Override
  public String toString() {
    return "{" + " name='" + getName() + "'" + ", offset='" + getOffset() + "'" + "}";
  }
}
