package com.github.rmkane.tools.domain.drawing;

import java.util.List;
import java.util.Objects;

public class Data {
  private List<Layer> layers;
  private List<Node> nodes;
  private List<Edge> edges;

  public Data() {}

  public Data(List<Layer> layers, List<Node> nodes, List<Edge> edges) {
    this.layers = layers;
    this.nodes = nodes;
    this.edges = edges;
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

  public List<Edge> getEdges() {
    return edges;
  }

  public void setEdges(List<Edge> edges) {
    this.edges = edges;
  }

  public Data layers(List<Layer> layers) {
    setLayers(layers);
    return this;
  }

  public Data nodes(List<Node> nodes) {
    setNodes(nodes);
    return this;
  }

  public Data edges(List<Edge> edges) {
    setEdges(edges);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Data)) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(layers, data.layers)
        && Objects.equals(nodes, data.nodes)
        && Objects.equals(edges, data.edges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layers, nodes, edges);
  }

  @Override
  public String toString() {
    return "{" + "layers=" + layers + ", nodes=" + nodes + ", edges=" + edges + '}';
  }
}
