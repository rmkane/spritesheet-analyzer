package com.github.rmkane.tools.domain.sprite;

import com.github.rmkane.tools.util.ImageUtils;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class SpriteInfo {
  private String name;
  private int startX;
  private int startY;
  private int endX;
  private int endY;

  public SpriteInfo() {}

  public SpriteInfo(String name, int startX, int startY, int endX, int endY) {
    this.name = name;
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStartX() {
    return this.startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return this.startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }

  public int getEndX() {
    return this.endX;
  }

  public void setEndX(int endX) {
    this.endX = endX;
  }

  public int getEndY() {
    return this.endY;
  }

  public void setEndY(int endY) {
    this.endY = endY;
  }

  public SpriteInfo name(String name) {
    setName(name);
    return this;
  }

  public SpriteInfo startX(int startX) {
    setStartX(startX);
    return this;
  }

  public SpriteInfo startY(int startY) {
    setStartY(startY);
    return this;
  }

  public SpriteInfo endX(int endX) {
    setEndX(endX);
    return this;
  }

  public SpriteInfo endY(int endY) {
    setEndY(endY);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SpriteInfo)) {
      return false;
    }
    SpriteInfo spriteInfo = (SpriteInfo) o;
    return Objects.equals(name, spriteInfo.name)
        && startX == spriteInfo.startX
        && startY == spriteInfo.startY
        && endX == spriteInfo.endX
        && endY == spriteInfo.endY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, startX, startY, endX, endY);
  }

  @Override
  public String toString() {
    return "{"
        + " name='"
        + getName()
        + "'"
        + ", startX='"
        + getStartX()
        + "'"
        + ", startY='"
        + getStartY()
        + "'"
        + ", endX='"
        + getEndX()
        + "'"
        + ", endY='"
        + getEndY()
        + "'"
        + "}";
  }

  public int getWidth() {
    return endX - startX;
  }

  public int getHeight() {
    return endY - startY;
  }

  public String getFilename() {
    return String.format("%s.png", name);
  }

  public static SpriteInfo parseLine(String line) {
    String[] tokens = line.trim().split("\\s+");
    SpriteInfo instance = new SpriteInfo();

    instance.name = tokens[0];
    instance.startX = Integer.parseInt(tokens[5]);
    instance.startY = Integer.parseInt(tokens[6]);
    instance.endX = Integer.parseInt(tokens[7]);
    instance.endY = Integer.parseInt(tokens[8]);

    return instance;
  }

  public BufferedImage extractImage(BufferedImage source) {
    return ImageUtils.extractSubImage(source, startX, startY, getWidth(), getHeight());
  }
}
