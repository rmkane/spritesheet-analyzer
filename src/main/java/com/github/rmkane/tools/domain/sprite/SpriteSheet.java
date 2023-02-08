package com.github.rmkane.tools.domain.sprite;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Objects;

public class SpriteSheet {
  private String name;
  private BufferedImage image;
  private Map<String, SpriteInfo> data;

  public SpriteSheet() {}

  public SpriteSheet(String name, BufferedImage image, Map<String, SpriteInfo> data) {
    this.name = name;
    this.image = image;
    this.data = data;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BufferedImage getImage() {
    return this.image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public Map<String, SpriteInfo> getData() {
    return this.data;
  }

  public void setData(Map<String, SpriteInfo> data) {
    this.data = data;
  }

  public SpriteSheet name(String name) {
    setName(name);
    return this;
  }

  public SpriteSheet image(BufferedImage image) {
    setImage(image);
    return this;
  }

  public SpriteSheet data(Map<String, SpriteInfo> data) {
    setData(data);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SpriteSheet)) {
      return false;
    }
    SpriteSheet spriteSheet = (SpriteSheet) o;
    return Objects.equals(name, spriteSheet.name)
        && Objects.equals(image, spriteSheet.image)
        && Objects.equals(data, spriteSheet.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, image, data);
  }

  @Override
  public String toString() {
    return "{"
        + " name='"
        + getName()
        + "'"
        + ", image='"
        + getImage()
        + "'"
        + ", data='"
        + getData()
        + "'"
        + "}";
  }

  public BufferedImage extractImage(String name) {
    return data.get(name).extractImage(image);
  }

  public static SpriteSheet create(String name, BufferedImage image, Map<String, SpriteInfo> data) {
    SpriteSheet instance = new SpriteSheet();

    instance.name = name;
    instance.image = image;
    instance.data = data;

    return instance;
  }
}
