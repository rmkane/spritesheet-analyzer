package com.github.rmkane.tools.domain.drawing;

import java.util.List;
import java.util.Objects;

public class Data {
  private List<Layer> layers;
  private List<Node> nodes;

  public Data() {}

  public Data(List<Layer> layers, List<Node> nodes) {
    this.layers = layers;
    this.nodes = nodes;
  }

  public List<Layer> getLayers() {
    return this.layers;
  }

  public void setLayers(List<Layer> layers) {
    this.layers = layers;
  }

  public List<Node> getNodes() {
    return this.nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public Data layers(List<Layer> layers) {
    setLayers(layers);
    return this;
  }

  public Data nodes(List<Node> nodes) {
    setNodes(nodes);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Data)) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(layers, data.layers) && Objects.equals(nodes, data.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layers, nodes);
  }

  @Override
  public String toString() {
    return "{" + " layers='" + getLayers() + "'" + ", nodes='" + getNodes() + "'" + "}";
  }
}
