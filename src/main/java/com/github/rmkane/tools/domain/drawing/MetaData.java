package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class MetaData {
  private String sheet;
  private Grid grid;
  private Dimension size;
  private Features features;

  public MetaData() {}

  public MetaData(String sheet, Grid grid, Dimension size, Features features) {
    this.sheet = sheet;
    this.grid = grid;
    this.size = size;
    this.features = features;
  }

  public String getSheet() {
    return this.sheet;
  }

  public void setSheet(String sheet) {
    this.sheet = sheet;
  }

  public Grid getGrid() {
    return this.grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public Dimension getSize() {
    return this.size;
  }

  public void setSize(Dimension size) {
    this.size = size;
  }

  public Features getFeatures() {
    return this.features;
  }

  public void setFeatures(Features features) {
    this.features = features;
  }

  public MetaData sheet(String sheet) {
    setSheet(sheet);
    return this;
  }

  public MetaData grid(Grid grid) {
    setGrid(grid);
    return this;
  }

  public MetaData size(Dimension size) {
    setSize(size);
    return this;
  }

  public MetaData features(Features features) {
    setFeatures(features);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof MetaData)) {
      return false;
    }
    MetaData metaData = (MetaData) o;
    return Objects.equals(sheet, metaData.sheet)
        && Objects.equals(grid, metaData.grid)
        && Objects.equals(size, metaData.size)
        && Objects.equals(features, metaData.features);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sheet, grid, size, features);
  }

  @Override
  public String toString() {
    return "{"
        + " sheet='"
        + getSheet()
        + "'"
        + ", grid='"
        + getGrid()
        + "'"
        + ", size='"
        + getSize()
        + "'"
        + ", features='"
        + getFeatures()
        + "'"
        + "}";
  }
}
