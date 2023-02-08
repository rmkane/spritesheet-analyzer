package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Node {

  private String name;
  private Cell cell;
  private String alignment;
  private String embelishment;
  private States states;

  public Node() {}

  public Node(String name, Cell cell, String alignment, String embelishment, States states) {
    this.name = name;
    this.cell = cell;
    this.alignment = alignment;
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

  public String getAlignment() {
    return this.alignment;
  }

  public void setAlignment(String alignment) {
    this.alignment = alignment;
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

  public Node alignment(String alignment) {
    setAlignment(alignment);
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
        && Objects.equals(alignment, node.alignment)
        && Objects.equals(embelishment, node.embelishment)
        && Objects.equals(states, node.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cell, alignment, embelishment, states);
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
        + ", alignment='"
        + getAlignment()
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
