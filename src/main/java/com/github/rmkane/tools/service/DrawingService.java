package com.github.rmkane.tools.service;

import com.github.rmkane.tools.domain.drawing.Drawing;
import com.github.rmkane.tools.domain.drawing.Edge;
import com.github.rmkane.tools.domain.drawing.Layer;
import com.github.rmkane.tools.domain.drawing.Node;
import com.github.rmkane.tools.domain.sprite.SpriteSheet;
import com.github.rmkane.tools.util.ImageUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DrawingService {
  private static final boolean SHOW_BACKGROUND = true;
  private static final boolean SHOW_ICONS = false;
  private static final boolean SHOW_EDGES = true;
  private static final boolean SHOW_LABELS = false;
  private static final boolean SHOW_GRID = false;

  public static void render(
      Map<String, SpriteSheet> spritesheets, Drawing drawing, String outputDir) {

    SpriteSheet spritesheet = spritesheets.get(drawing.getMetadata().getSheet());

    int width = drawing.getMetadata().getSize().getWidth();
    int height = drawing.getMetadata().getSize().getHeight();
    BufferedImage root = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    if (SHOW_BACKGROUND) {
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
    if (SHOW_EDGES) {
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
        float x1 = gridOffsetX + cellOffsetX + from.getCell().getColumn() * cellWidth;
        float y1 = gridOffsetY + cellOffsetX + from.getCell().getRow() * cellHeight;
        float x2 = gridOffsetX + cellOffsetX + to.getCell().getColumn() * cellWidth;
        float y2 = gridOffsetY + cellOffsetX + to.getCell().getRow() * cellHeight;
        float x3 = (x1 + x2) / 2.0f;
        float y3 = (y1 + y2) / 2.0f;
        float offX = diagonal.getWidth() / 2;
        float offY = diagonal.getHeight() / 2;

        if (edge.getFlipped()) {
          diagonal = ImageUtils.flipHorizontally(diagonal);
        }

        ImageUtils.draw(diagonal, root, (int) (x3 - offX), (int) (y3 - offY));
      }
    }

    // Draw grid
    if (SHOW_GRID) {
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

      if (SHOW_ICONS) {
        BufferedImage embellishment = spritesheet.extractImage(node.getEmbelishment());
        BufferedImage icon = spritesheet.extractImage(node.getStates().getEnabled());
        float offsetX = justify(icon.getWidth(), cellWidth, "center");
        float offsetY = align(icon.getHeight(), cellHeight, node.getAlignment());
        ImageUtils.draw(embellishment, root, (int) x, (int) y);
        ImageUtils.draw(icon, root, (int) (x + offsetX), (int) (y + offsetY));
      }

      // Draw label
      if (SHOW_LABELS) {
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
        return (targetHeight - sourceHeight) / 2;
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
        return (targetWidth - sourceWidth) / 2;
      case "right":
        return targetWidth - sourceWidth;
      case "left":
      default:
        return 0;
    }
  }
}
