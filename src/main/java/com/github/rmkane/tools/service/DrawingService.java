package com.github.rmkane.tools.service;

import com.github.rmkane.tools.domain.drawing.Drawing;
import com.github.rmkane.tools.domain.drawing.Edge;
import com.github.rmkane.tools.domain.drawing.Layer;
import com.github.rmkane.tools.domain.drawing.Node;
import com.github.rmkane.tools.domain.sprite.SpriteSheet;
import com.github.rmkane.tools.util.ImageUtils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DrawingService {

  private static final boolean DEBUG = false;

  public static void render(
      Map<String, SpriteSheet> spritesheets, Drawing drawing, String outputDir) {

    SpriteSheet spritesheet = spritesheets.get(drawing.getMetadata().getSheet());

    int width = drawing.getMetadata().getSize().getWidth();
    int height = drawing.getMetadata().getSize().getHeight();
    BufferedImage root = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    if (drawing.getMetadata().getFeatures().getBackground()) {
      for (Layer layer : drawing.getData().getLayers()) {
        ImageUtils.draw(
            spritesheet.extractImage(layer.getName()),
            root,
            layer.getOffset().getX(),
            layer.getOffset().getY());
      }
    }

    // Grid
    int gridOffsetX = drawing.getMetadata().getGrid().getOffset().getX();
    int gridOffsetY = drawing.getMetadata().getGrid().getOffset().getY();
    int cellWidth = drawing.getMetadata().getGrid().getSize().getWidth();
    int cellHeight = drawing.getMetadata().getGrid().getSize().getHeight();
    float cellOffsetX = cellWidth / 2.0f;
    float cellOffsetY = cellHeight / 2.0f;

    // Edges
    if (drawing.getMetadata().getFeatures().getEdges()) {
      float initialOffsetX = gridOffsetX + cellOffsetX;
      float initialOffsetY = gridOffsetY + cellOffsetY;
      for (Edge edge : drawing.getData().getEdges()) {
        Node from =
            drawing.getData().getNodes().stream()
                .filter(node -> node.getName().equals(edge.getDirection().getFrom()))
                .findFirst()
                .get();
        Node to =
            drawing.getData().getNodes().stream()
                .filter(node -> node.getName().equals(edge.getDirection().getTo()))
                .findFirst()
                .get();
        BufferedImage diagonal = spritesheet.extractImage(edge.getStates().getEnabled());
        float x1 = initialOffsetX + from.getCell().getColumn() * cellWidth;
        float y1 = initialOffsetY + from.getCell().getRow() * cellHeight;
        float x2 = initialOffsetX + to.getCell().getColumn() * cellWidth;
        float y2 = initialOffsetY + to.getCell().getRow() * cellHeight;
        float x3 = (x1 + x2) / 2.0f;
        float y3 = (y1 + y2) / 2.0f;
        float offX = diagonal.getWidth() / 2.0f;
        float offY = diagonal.getHeight() / 2.0f;
        int nudgeX = edge.getNudge().getX();
        int nudgeY = edge.getNudge().getY();
        int centerX = (int) (x3 - offX + nudgeX);
        int centerY = (int) (y3 - offY + nudgeY);

        if (edge.getFlipped()) {
          diagonal = ImageUtils.flipHorizontally(diagonal);
        }

        ImageUtils.draw(diagonal, root, centerX, centerY);

        if (DEBUG) {
          Graphics2D g = (Graphics2D) root.getGraphics();

          g.setColor(new Color(1.0f, 0.3f, 1.0f, 0.5f));
          g.fillRect(centerX, centerY, diagonal.getWidth(), diagonal.getHeight());

          g.setColor(new Color(0.3f, 1.0f, 0.3f, 0.8f));
          g.fillOval((int) (x1 - 4), (int) (y1 - 4), 8, 8);

          g.setColor(new Color(0.3f, 1.0f, 0.3f, 0.8f));
          g.fillOval((int) (x2 - 4), (int) (y2 - 4), 8, 8);

          g.setColor(new Color(1.0f, 1.0f, 0.3f, 0.8f));
          g.fillOval((int) (x3 - 4), (int) (y3 - 4), 8, 8);
        }
      }
    }

    // Draw grid
    if (drawing.getMetadata().getFeatures().getGrid()) {
      int offX = gridOffsetX + (cellWidth * 3);
      int offY = gridOffsetY + (cellHeight * 3);
      Color oddColor = new Color(1.0f, 0.0f, 0.5f, 0.33f);
      Color evenColor = new Color(0.0f, 0.5f, 1.0f, 0.33f);
      ImageUtils.drawCheckerBoard(
          root, 12, 9, cellWidth, cellHeight, oddColor, evenColor, offX, offY);
    }

    // Draw nodes
    int fontSize = (int) (cellHeight * 0.667);
    for (Node node : drawing.getData().getNodes()) {
      int row = node.getCell().getRow();
      int column = node.getCell().getColumn();
      float x = gridOffsetX + (cellWidth * column);
      float y = gridOffsetY + (cellHeight * row);

      if (drawing.getMetadata().getFeatures().getIcons()) {
        BufferedImage embellishment = spritesheet.extractImage(node.getEmbellishment());
        BufferedImage icon = spritesheet.extractImage(node.getStates().getEnabled());
        float offsetX = justify(icon.getWidth(), cellWidth, "center");
        float offsetY = align(icon.getHeight(), cellHeight, node.getAlignment());
        ImageUtils.draw(embellishment, root, (int) x, (int) y);
        ImageUtils.draw(icon, root, (int) (x + offsetX), (int) (y + offsetY));
      }

      // Draw label
      if (drawing.getMetadata().getFeatures().getLabels()) {
        int x1 = (int) (x + cellOffsetX);
        int y1 = (int) (y + cellOffsetY);
        ImageUtils.drawLabelFromCenter(root, node.getName(), x1, y1, Color.GREEN, fontSize);
      }
    }

    // ImageUtils.display(ImageUtils.scale(root, 0.5, 0.5));
    ImageUtils.display(root);
  }

  public static float align(int sourceHeight, int targetHeight, String alignment) {
    switch (alignment) {
      case "middle":
        return (targetHeight - sourceHeight) / 2.0f;
      case "bottom":
        return targetHeight - sourceHeight;
      case "top":
      default:
        return 0;
    }
  }

  public static float justify(int sourceWidth, int targetWidth, String justification) {
    switch (justification) {
      case "center":
        return (targetWidth - sourceWidth) / 2.0f;
      case "right":
        return targetWidth - sourceWidth;
      case "left":
      default:
        return 0;
    }
  }
}
