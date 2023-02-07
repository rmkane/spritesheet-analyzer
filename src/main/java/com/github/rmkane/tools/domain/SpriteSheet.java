package com.github.rmkane.tools.domain;

import java.awt.image.BufferedImage;
import java.util.List;

public class SpriteSheet {
  private String name;
  private BufferedImage image;
  private List<SpriteInfo> data;

  public SpriteSheet(String name, BufferedImage image, List<SpriteInfo> data) {
    this.name = name;
    this.image = image;
    this.data = data;
  }

  public String getName() {
    return name;
  }

  public List<SpriteInfo> getData() {
    return data;
  }

  public BufferedImage getImage() {
    return image;
  }
}
