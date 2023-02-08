package com.github.rmkane.tools.service;

import com.github.rmkane.tools.domain.sprite.SpriteSheet;
import com.github.rmkane.tools.util.ImageUtils;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DrawingService {
  public static void render(Map<String, SpriteSheet> spritesheets, String outputDir) {
    SpriteSheet spritesheet = spritesheets.get("2DUIDarkAlchemy");
    BufferedImage base = spritesheet.extractImage("DarkAlchemy_Base");
    BufferedImage recipes = spritesheet.extractImage("DarkAlchemy_Recipes_Background");
    BufferedImage embellishment =
        spritesheet.extractImage("DarkAlchemy_Node_Embellishment_ABCDEFGH");

    BufferedImage root = ImageUtils.sizedImage(base);

    // Base
    ImageUtils.draw(base, root);
    ImageUtils.draw(recipes, root, 50, 180);

    // Nodes
    int gridOffsetX = 47;
    int gridOffsetY = 49;
    int gridSizeWidth = 64;
    int gridSizeHeight = 64;
    int node_0_cellRow = 3;
    int node_0_cellColumn = 7;

    int x = gridOffsetX + (gridSizeWidth * node_0_cellColumn);
    int y = gridOffsetY + (gridSizeHeight * node_0_cellRow);

    ImageUtils.draw(embellishment, root, x, y);

    ImageUtils.display(ImageUtils.scale(root, 0.5, 0.5));
  }
}
