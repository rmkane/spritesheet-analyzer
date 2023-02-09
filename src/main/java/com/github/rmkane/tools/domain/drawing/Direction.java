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
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Direction direction = (Direction) o;
    return Objects.equals(from, direction.from) && Objects.equals(to, direction.to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public String toString() {
    return "{" + "from='" + from + '\'' + ", to='" + to + '\'' + '}';
  }
}
