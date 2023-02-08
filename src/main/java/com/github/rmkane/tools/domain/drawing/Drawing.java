package com.github.rmkane.tools.domain.drawing;

import java.util.Objects;

public class Drawing {
  private MetaData metadata;
  private Data data;

  public Drawing() {}

  public Drawing(MetaData metadata, Data data) {
    this.metadata = metadata;
    this.data = data;
  }

  public MetaData getMetadata() {
    return this.metadata;
  }

  public void setMetadata(MetaData metadata) {
    this.metadata = metadata;
  }

  public Data getData() {
    return this.data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public Drawing metadata(MetaData metadata) {
    setMetadata(metadata);
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
    return Objects.equals(metadata, drawing.metadata) && Objects.equals(data, drawing.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadata, data);
  }

  @Override
  public String toString() {
    return "{" + " metadata='" + getMetadata() + "'" + ", data='" + getData() + "'" + "}";
  }
}
