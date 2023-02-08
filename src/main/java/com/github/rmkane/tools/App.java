package com.github.rmkane.tools;

import com.github.rmkane.tools.domain.drawing.Drawing;
import com.github.rmkane.tools.domain.sprite.SpriteSheet;
import com.github.rmkane.tools.service.DrawingService;
import com.github.rmkane.tools.service.SpriteSheetService;
import com.github.rmkane.tools.util.JsonUtils;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  private static final String RESOURCE_ROOT = "spritesheets";
  private static final String OUTPUT_DIR = "output";
  private static final String DRAWING_JSON = "data/drawing.json";

  public static void main(String[] args) {
    App app = new App();
    Thread t = new Thread(app);
    t.start();
  }

  @Override
  public void run() {
    logger.info("Starting spritesheet analyzer...");

    Map<String, SpriteSheet> spritesheets = SpriteSheetService.loadSpritesheets(RESOURCE_ROOT);

    SpriteSheetService.processSpritesheets(spritesheets, OUTPUT_DIR);

    Drawing drawing = JsonUtils.loadJson(DRAWING_JSON, Drawing.class);

    DrawingService.render(spritesheets, drawing, OUTPUT_DIR);

    logger.info("Completed processing...");
  }
}
