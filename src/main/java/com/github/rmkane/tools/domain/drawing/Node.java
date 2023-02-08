package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Node {

  private String name;
  private Cell cell;
  private String origin;
  private String embelishment;
  private States states;

  public Node() {}

  public Node(String name, Cell cell, String origin, String embelishment, States states) {
    this.name = name;
    this.cell = cell;
    this.origin = origin;
    this.embelishment = embelishment;
    this.states = states;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Cell getCell() {
    return this.cell;
  }

  public void setCell(Cell cell) {
    this.cell = cell;
  }

  public String getOrigin() {
    return this.origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getEmbelishment() {
    return this.embelishment;
  }

  public void setEmbelishment(String embelishment) {
    this.embelishment = embelishment;
  }

  public States getStates() {
    return this.states;
  }

  public void setStates(States states) {
    this.states = states;
  }

  public Node name(String name) {
    setName(name);
    return this;
  }

  public Node cell(Cell cell) {
    setCell(cell);
    return this;
  }

  public Node origin(String origin) {
    setOrigin(origin);
    return this;
  }

  public Node embelishment(String embelishment) {
    setEmbelishment(embelishment);
    return this;
  }

  public Node states(States states) {
    setStates(states);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Node)) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(name, node.name)
        && Objects.equals(cell, node.cell)
        && Objects.equals(origin, node.origin)
        && Objects.equals(embelishment, node.embelishment)
        && Objects.equals(states, node.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cell, origin, embelishment, states);
  }

  @Override
  public String toString() {
    return "{"
        + " name='"
        + getName()
        + "'"
        + ", cell='"
        + getCell()
        + "'"
        + ", origin='"
        + getOrigin()
        + "'"
        + ", embelishment='"
        + getEmbelishment()
        + "'"
        + ", states='"
        + getStates()
        + "'"
        + "}";
  }
}
