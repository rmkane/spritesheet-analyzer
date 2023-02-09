package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Features {
  private boolean background;
  private boolean edges;
  private boolean grid;
  private boolean icons;
  private boolean labels;

  public Features() {}

  public Features(boolean background, boolean edges, boolean grid, boolean icons, boolean labels) {
    this.background = background;
    this.edges = edges;
    this.grid = grid;
    this.icons = icons;
    this.labels = labels;
  }

  public boolean isBackground() {
    return this.background;
  }

  public boolean getBackground() {
    return this.background;
  }

  public void setBackground(boolean background) {
    this.background = background;
  }

  public boolean isEdges() {
    return this.edges;
  }

  public boolean getEdges() {
    return this.edges;
  }

  public void setEdges(boolean edges) {
    this.edges = edges;
  }

  public boolean isGrid() {
    return this.grid;
  }

  public boolean getGrid() {
    return this.grid;
  }

  public void setGrid(boolean grid) {
    this.grid = grid;
  }

  public boolean isIcons() {
    return this.icons;
  }

  public boolean getIcons() {
    return this.icons;
  }

  public void setIcons(boolean icons) {
    this.icons = icons;
  }

  public boolean isLabels() {
    return this.labels;
  }

  public boolean getLabels() {
    return this.labels;
  }

  public void setLabels(boolean labels) {
    this.labels = labels;
  }

  public Features background(boolean background) {
    setBackground(background);
    return this;
  }

  public Features edges(boolean edges) {
    setEdges(edges);
    return this;
  }

  public Features grid(boolean grid) {
    setGrid(grid);
    return this;
  }

  public Features icons(boolean icons) {
    setIcons(icons);
    return this;
  }

  public Features labels(boolean labels) {
    setLabels(labels);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Features)) {
      return false;
    }
    Features features = (Features) o;
    return background == features.background
        && edges == features.edges
        && grid == features.grid
        && icons == features.icons
        && labels == features.labels;
  }

  @Override
  public int hashCode() {
    return Objects.hash(background, edges, grid, icons, labels);
  }

  @Override
  public String toString() {
    return "{"
        + " background='"
        + isBackground()
        + "'"
        + ", edges='"
        + isEdges()
        + "'"
        + ", grid='"
        + isGrid()
        + "'"
        + ", icons='"
        + isIcons()
        + "'"
        + ", labels='"
        + isLabels()
        + "'"
        + "}";
  }
}
