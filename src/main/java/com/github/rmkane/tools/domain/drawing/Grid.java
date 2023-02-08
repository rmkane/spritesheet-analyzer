package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Grid {
  private Point offset;
  private Dimension size;

  public Grid() {}

  public Grid(Point offset, Dimension size) {
    this.offset = offset;
    this.size = size;
  }

  public Point getOffset() {
    return this.offset;
  }

  public void setOffset(Point offset) {
    this.offset = offset;
  }

  public Dimension getSize() {
    return this.size;
  }

  public void setSize(Dimension size) {
    this.size = size;
  }

  public Grid offset(Point offset) {
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
