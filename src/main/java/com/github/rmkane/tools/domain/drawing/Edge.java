package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Edge {
  private Direction direction;
  private States states;
  private Boolean flipped;

  public Edge() {}

  public Edge(Direction direction, States states, Boolean flipped) {
    this.direction = direction;
    this.states = states;
    this.flipped = flipped;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public States getStates() {
    return this.states;
  }

  public void setStates(States states) {
    this.states = states;
  }

  public Boolean isFlipped() {
    return this.flipped;
  }

  public Boolean getFlipped() {
    return this.flipped;
  }

  public void setFlipped(Boolean flipped) {
    this.flipped = flipped;
  }

  public Edge direction(Direction direction) {
    setDirection(direction);
    return this;
  }

  public Edge states(States states) {
    setStates(states);
    return this;
  }

  public Edge flipped(Boolean flipped) {
    setFlipped(flipped);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Edge)) {
      return false;
    }
    Edge edge = (Edge) o;
    return Objects.equals(direction, edge.direction)
        && Objects.equals(states, edge.states)
        && Objects.equals(flipped, edge.flipped);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, states, flipped);
  }

  @Override
  public String toString() {
    return "{"
        + " direction='"
        + getDirection()
        + "'"
        + ", states='"
        + getStates()
        + "'"
        + ", flipped='"
        + isFlipped()
        + "'"
        + "}";
  }
}
