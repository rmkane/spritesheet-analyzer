package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Dimension {
  private int width;
  private int height;

  public Dimension() {}

  public Dimension(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return this.width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Dimension width(int width) {
    setWidth(width);
    return this;
  }

  public Dimension height(int height) {
    setHeight(height);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Dimension)) {
      return false;
    }
    Dimension dimension = (Dimension) o;
    return width == dimension.width && height == dimension.height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }

  @Override
  public String toString() {
    return "{" + " width='" + getWidth() + "'" + ", height='" + getHeight() + "'" + "}";
  }
}
