package com.github.rmkane.tools.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
  private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

  public static BufferedImage loadImage(File imageFile) {
    try {
      return ImageIO.read(imageFile);
    } catch (IOException e) {
      logger.error("Error reading image");
    }
    return null;
  }

  public static boolean writeImage(String filename, BufferedImage image) {
    try {
      return ImageIO.write(image, "png", new File(filename));
    } catch (IOException e) {
      logger.error("Failed to write image", e);
    }
    return false;
  }

  public static BufferedImage extractSubImage(
      BufferedImage source, int x, int y, int width, int height) {
    return source.getSubimage(x, y, width, height);
  }
}
