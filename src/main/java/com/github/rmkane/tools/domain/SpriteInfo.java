package com.github.rmkane.tools.domain;

import com.github.rmkane.tools.util.ImageUtils;
import java.awt.image.BufferedImage;

public class SpriteInfo {
  private String name;
  private int startX;
  private int startY;
  private int endX;
  private int endY;

  public String getName() {
    return name;
  }

  public int getStartX() {
    return startX;
  }

  public int getStartY() {
    return startY;
  }

  public int getEndX() {
    return endX;
  }

  public int getEndY() {
    return endY;
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

  @Override
  public String toString() {
    return "{\"name\":"
        + name
        + "\",\"startX\":"
        + startX
        + ",\"startY\":"
        + startY
        + ",\"endX\":"
        + endX
        + ",\"endY\":"
        + endY
        + "}";
  }
}
