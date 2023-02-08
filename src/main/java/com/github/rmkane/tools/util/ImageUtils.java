package com.github.rmkane.tools.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
  private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

  public static void display(BufferedImage image) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void draw(BufferedImage source, BufferedImage destination) {
    draw(source, destination, 0, 0);
  }

  public static void draw(
      BufferedImage source, BufferedImage destination, int offsetX, int offsetY) {
    Graphics2D graphics = destination.createGraphics();
    graphics.drawImage(source, offsetX, offsetY, null);
    graphics.dispose();
  }

  public static void drawCheckerBoard(
      BufferedImage source,
      int rows,
      int columns,
      int cellWidth,
      int cellHeight,
      Color oddColor,
      Color evenColor,
      int offsetX,
      int offsetY) {
    drawCheckerBoard(
        (Graphics2D) source.getGraphics(),
        rows,
        columns,
        cellWidth,
        cellHeight,
        oddColor,
        evenColor,
        offsetX,
        offsetY);
  }

  public static void drawCheckerBoard(
      Graphics2D graphics,
      int rows,
      int columns,
      int cellWidth,
      int cellHeight,
      Color oddColor,
      Color evenColor,
      int offsetX,
      int offsetY) {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        int x = offsetX + (cellWidth * column);
        int y = offsetY + (cellHeight * row);
        boolean isOdd = (row % 2 == 1) ^ (column % 2 == 0);
        graphics.setColor(isOdd ? oddColor : evenColor);
        graphics.fillRect(x, y, cellWidth, cellHeight);
      }
    }
  }

  public static void drawLabelFromCenter(
      BufferedImage source, String label, int x, int y, Color color, int fontSize) {
    drawLabelFromCenter((Graphics2D) source.getGraphics(), label, x, y, color, fontSize);
  }

  public static void drawLabelFromCenter(
      Graphics2D graphics, String label, int x, int y, Color color, int fontSize) {
    graphics.setColor(color);
    graphics.setFont(new Font("monospace", Font.PLAIN, fontSize)); // monospace not necessary
    graphics.setRenderingHint(
        RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    TextLayout textLayout =
        new TextLayout(label, graphics.getFont(), graphics.getFontRenderContext());
    double textHeight = textLayout.getBounds().getHeight();
    double textWidth = textLayout.getBounds().getWidth();

    graphics.drawString(label, x - (int) textWidth / 2, y + (int) textHeight / 2);
  }

  public static BufferedImage loadImage(File imageFile) {
    try {
      return ImageIO.read(imageFile);
    } catch (IOException e) {
      logger.error("Error reading image");
    }
    return null;
  }

  public static BufferedImage scale(BufferedImage image, double sx, double sy) {
    int width = (int) (image.getWidth() * sx);
    int height = (int) (image.getHeight() * sy);
    BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    AffineTransform matrix = AffineTransform.getScaleInstance(sx, sy);
    AffineTransformOp scaleOp = new AffineTransformOp(matrix, AffineTransformOp.TYPE_BILINEAR);
    Graphics2D graphics = (Graphics2D) scaled.getGraphics();
    graphics.drawImage(image, scaleOp, 0, 0);
    graphics.dispose();
    return scaled;
  }

  public static BufferedImage sizedImage(BufferedImage image) {
    return new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
  }

  public static void writeImage(String filename, BufferedImage image) {
    try {
      ImageIO.write(image, "png", new File(filename));
    } catch (IOException e) {
      logger.error("Failed to write image", e);
    }
  }
}
