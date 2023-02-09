package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Direction {
  private String from;
  private String to;

  public Direction() {}

  public Direction(String from, String to) {
    this.from = from;
    this.to = to;
  }

  public String getFrom() {
    return this.from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return this.to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Direction from(String from) {
    setFrom(from);
    return this;
  }

  public Direction to(String to) {
    setTo(to);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Direction)) {
      return false;
    }
    Direction direction = (Direction) o;
    return Objects.equals(from, direction.from) && Objects.equals(to, direction.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public String toString() {
    return "{" + " from='" + getFrom() + "'" + ", to='" + getTo() + "'" + "}";
  }
}
