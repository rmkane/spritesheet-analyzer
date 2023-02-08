package com.github.rmkane.tools.domain.drawing;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Objects;

public class Grid {
  private Point2D offset;
  private Dimension size;

  public Grid() {}

  public Grid(Point2D offset, Dimension size) {
    this.offset = offset;
    this.size = size;
  }

  public Point2D getOffset() {
    return this.offset;
  }

  public void setOffset(Point2D offset) {
    this.offset = offset;
  }

  public Dimension getSize() {
    return this.size;
  }

  public void setSize(Dimension size) {
    this.size = size;
  }

  public Grid offset(Point2D offset) {
    setOffset(offset);
    return this;
  }

  public Grid size(Dimension size) {
    setSize(size);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Grid)) {
      return false;
    }
    Grid grid = (Grid) o;
    return Objects.equals(offset, grid.offset) && Objects.equals(size, grid.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, size);
  }

  @Override
  public String toString() {
    return "{" + " offset='" + getOffset() + "'" + ", size='" + getSize() + "'" + "}";
  }
}
