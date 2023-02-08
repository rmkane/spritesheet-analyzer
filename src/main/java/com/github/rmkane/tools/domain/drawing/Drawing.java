package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Drawing {
  private MetaData metaData;
  private Data data;

  public Drawing() {}

  public Drawing(MetaData metaData, Data data) {
    this.metaData = metaData;
    this.data = data;
  }

  public MetaData getMetaData() {
    return this.metaData;
  }

  public void setMetaData(MetaData metaData) {
    this.metaData = metaData;
  }

  public Data getData() {
    return this.data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public Drawing metaData(MetaData metaData) {
    setMetaData(metaData);
    return this;
  }

  public Drawing data(Data data) {
    setData(data);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Drawing)) {
      return false;
    }
    Drawing drawing = (Drawing) o;
    return Objects.equals(metaData, drawing.metaData) && Objects.equals(data, drawing.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metaData, data);
  }

  @Override
  public String toString() {
    return "{" + " metaData='" + getMetaData() + "'" + ", data='" + getData() + "'" + "}";
  }
}
