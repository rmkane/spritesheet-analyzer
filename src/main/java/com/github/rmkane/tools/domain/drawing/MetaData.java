package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class MetaData {
  private String sheet;
  private Grid grid;

  public MetaData() {}

  public MetaData(String sheet, Grid grid) {
    this.sheet = sheet;
    this.grid = grid;
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

  public MetaData sheet(String sheet) {
    setSheet(sheet);
    return this;
  }

  public MetaData grid(Grid grid) {
    setGrid(grid);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof MetaData)) {
      return false;
    }
    MetaData metaData = (MetaData) o;
    return Objects.equals(sheet, metaData.sheet) && Objects.equals(grid, metaData.grid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sheet, grid);
  }

  @Override
  public String toString() {
    return "{" + " sheet='" + getSheet() + "'" + ", grid='" + getGrid() + "'" + "}";
  }
}
