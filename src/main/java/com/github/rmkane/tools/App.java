package com.github.rmkane.tools;

import com.github.rmkane.tools.util.SpriteSheetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  private static final String RESOURCE_ROOT = "spritesheets";
  private static final String OUTPUT_DIR = "output";

  public static void main(String[] args) {
    App app = new App();
    Thread t = new Thread(app);
    t.start();
  }

  @Override
  public void run() {
    logger.info("Starting spritesheet analyzer...");
    SpriteSheetUtils.processSpritesheets(RESOURCE_ROOT, OUTPUT_DIR);
    logger.info("Completed processing...");
  }
}
