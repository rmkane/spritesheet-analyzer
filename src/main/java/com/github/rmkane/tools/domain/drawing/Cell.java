package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Cell {
  private int row;
  private int column;

  public Cell() {}

  public Cell(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return this.row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return this.column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public Cell row(int row) {
    setRow(row);
    return this;
  }

  public Cell column(int column) {
    setColumn(column);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Cell)) {
      return false;
    }
    Cell cell = (Cell) o;
    return row == cell.row && column == cell.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return "{" + " row='" + getRow() + "'" + ", column='" + getColumn() + "'" + "}";
  }
}
