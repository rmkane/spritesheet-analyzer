package com.github.rmkane.tools.service;

import com.github.rmkane.tools.domain.drawing.Drawing;
import com.github.rmkane.tools.domain.drawing.Layer;
import com.github.rmkane.tools.domain.drawing.Node;
import com.github.rmkane.tools.domain.sprite.SpriteSheet;
import com.github.rmkane.tools.util.ImageUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DrawingService {
  public static void render(
      Map<String, SpriteSheet> spritesheets, Drawing drawing, String outputDir) {

    SpriteSheet spritesheet = spritesheets.get(drawing.getMetadata().getSheet());

    int width = drawing.getMetadata().getSize().getWidth();
    int height = drawing.getMetadata().getSize().getHeight();
    BufferedImage root = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for (Layer layer : drawing.getData().getLayers()) {
      ImageUtils.draw(
          spritesheet.extractImage(layer.getName()),
          root,
          layer.getOffset().getX(),
          layer.getOffset().getY());
    }

    // Nodes
    int gridOffsetX = drawing.getMetadata().getGrid().getOffset().getX();
    int gridOffsetY = drawing.getMetadata().getGrid().getOffset().getY();
    int cellWidth = drawing.getMetadata().getGrid().getSize().getWidth();
    int cellHeight = drawing.getMetadata().getGrid().getSize().getHeight();

    // Draw grid
    int offX = gridOffsetX + (cellWidth * 3);
    int offY = gridOffsetY + (cellHeight * 3);
    Color oddColor = new Color(1.0f, 0.0f, 0.5f, 0.33f);
    Color evenColor = new Color(0.0f, 0.5f, 1.0f, 0.33f);
    ImageUtils.drawCheckerBoard(
        root, 12, 9, cellWidth, cellHeight, oddColor, evenColor, offX, offY);

    // Draw nodes
    int fontSize = (int) (cellHeight * 0.667);
    for (Node node : drawing.getData().getNodes()) {
      int row = node.getCell().getRow();
      int column = node.getCell().getColumn();
      int x = gridOffsetX + (cellWidth * column);
      int y = gridOffsetY + (cellHeight * row);

      BufferedImage embellishment = spritesheet.extractImage(node.getEmbelishment());
      ImageUtils.draw(embellishment, root, x, y);

      BufferedImage icon = spritesheet.extractImage(node.getStates().getEnabled());

      int offsetX = justify(icon.getWidth(), cellWidth, "center");
      int offsetY = align(icon.getHeight(), cellHeight, node.getAlignment());

      ImageUtils.draw(icon, root, x + offsetX, y + offsetY);

      int x1 = x + (cellWidth / 2);
      int y1 = y + (cellHeight / 2);
      ImageUtils.drawLabelFromCenter(root, node.getName(), x1, y1, Color.GREEN, fontSize);
    }

    ImageUtils.display(ImageUtils.scale(root, 0.5, 0.5));
  }

  public static int align(int sourceHeight, int targetHeight, String alignment) {
    switch (alignment) {
      case "middle":
        return (targetHeight - sourceHeight) / 2;
      case "bottom":
        return targetHeight - sourceHeight;
      case "top":
      default:
        return 0;
    }
  }

  public static int justify(int sourceWidth, int targetWidth, String justification) {
    switch (justification) {
      case "center":
        return (targetWidth - sourceWidth) / 2;
      case "right":
        return targetWidth - sourceWidth;
      case "left":
      default:
        return 0;
    }
  }
}
