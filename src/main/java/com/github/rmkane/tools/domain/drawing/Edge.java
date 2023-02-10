package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Edge {
  private Direction direction;
  private States states;
  private Boolean flipped;
  private Point nudge;

  public Edge() {}

  public Edge(Direction direction, States states, Boolean flipped, Point nudge) {
    this.direction = direction;
    this.states = states;
    this.flipped = flipped;
    this.nudge = nudge;
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

  public Point getNudge() {
    return this.nudge;
  }

  public void setNudge(Point nudge) {
    this.nudge = nudge;
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

  public Edge nudge(Point nudge) {
    setNudge(nudge);
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
        && Objects.equals(flipped, edge.flipped)
        && Objects.equals(nudge, edge.nudge);
  }

  @Override
  public int hashCode() {
    return Objects.hash(direction, states, flipped, nudge);
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
        + ", nudge='"
        + getNudge()
        + "'"
        + "}";
  }
}
