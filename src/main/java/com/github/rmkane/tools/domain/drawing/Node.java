package com.github.rmkane.tools.domain.drawing;

import java.util.List;
import java.util.Objects;

public class Node {

  private String name;
  private List<String> parents;
  private Cell cell;
  private String alignment;
  private String embellishment;
  private States states;

  public Node() {}

  public Node(
      String name,
      List<String> parents,
      Cell cell,
      String alignment,
      String embellishment,
      States states) {
    this.name = name;
    this.parents = parents;
    this.cell = cell;
    this.alignment = alignment;
    this.embellishment = embellishment;
    this.states = states;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getParents() {
    return this.parents;
  }

  public void setParents(List<String> parents) {
    this.parents = parents;
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

  public String getEmbellishment() {
    return this.embellishment;
  }

  public void setEmbellishment(String embellishment) {
    this.embellishment = embellishment;
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

  public Node parents(List<String> parents) {
    setParents(parents);
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

  public Node embellishment(String embellishment) {
    setEmbellishment(embellishment);
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
        && Objects.equals(parents, node.parents)
        && Objects.equals(cell, node.cell)
        && Objects.equals(alignment, node.alignment)
        && Objects.equals(embellishment, node.embellishment)
        && Objects.equals(states, node.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parents, cell, alignment, embellishment, states);
  }

  @Override
  public String toString() {
    return "{"
        + " name='"
        + getName()
        + "'"
        + ", parents='"
        + getParents()
        + "'"
        + ", cell='"
        + getCell()
        + "'"
        + ", alignment='"
        + getAlignment()
        + "'"
        + ", embellishment='"
        + getEmbellishment()
        + "'"
        + ", states='"
        + getStates()
        + "'"
        + "}";
  }
}
