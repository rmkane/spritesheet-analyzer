package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Edge {
  private Direction direction;
  private States states;

  public Edge() {}

  public Edge(Direction direction, States states) {
    this.direction = direction;
    this.states = states;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public States getStates() {
    return states;
  }

  public void setStates(States states) {
    this.states = states;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Edge edge = (Edge) o;
    return Objects.equals(direction, edge.direction) && Objects.equals(states, edge.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, states);
  }

  @Override
  public String toString() {
    return "{" + "direction=" + direction + ", states=" + states + '}';
  }
}
