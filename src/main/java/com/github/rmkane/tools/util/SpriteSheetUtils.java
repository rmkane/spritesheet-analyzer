package com.github.rmkane.tools.util;

import com.github.rmkane.tools.domain.SpriteInfo;
import com.github.rmkane.tools.domain.SpriteSheet;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpriteSheetUtils {
  private static final Logger logger = LoggerFactory.getLogger(SpriteSheetUtils.class);

  private static final Pattern atlasPattern, groupNamePattern, imagePattern;

  static {
    groupNamePattern = Pattern.compile("^[a-z0-9]+", Pattern.CASE_INSENSITIVE);
    imagePattern = Pattern.compile("^[a-z0-9]+\\.png", Pattern.CASE_INSENSITIVE);
    atlasPattern = Pattern.compile("^[a-z0-9]+_atlas\\.txt", Pattern.CASE_INSENSITIVE);
  }

  public static void processSpritesheets(String resourceDir, String outputDir) {
    if (FileUtils.mkdir(outputDir)) {
      logger.debug("Creating output directory: {}", outputDir);
    }
    processSpritesheets(loadSpritesheets(resourceDir), outputDir);
  }

  private static List<SpriteSheet> loadSpritesheets(String resourcePath) {
    Map<String, List<File>> groups =
        groupAssetsByCommonName(FileUtils.listFilesInDirectory(resourcePath));
    if (!CollectionUtils.isMapValid(groups, v -> v.size() > 1)) {
      logger.error("Invalid asset structure");
      throw new RuntimeException("Invalid asset structure");
    }
    return processGroups(groups);
  }

  private static void processSpritesheets(List<SpriteSheet> spritesheets, String outputDir) {
    spritesheets.forEach(sheet -> SpriteSheetUtils.processSpritesheet(sheet, outputDir));
  }

  private static void processSpritesheet(SpriteSheet spritesheet, String outputDir) {
    String group = spritesheet.getName();
    if (FileUtils.mkdir(outputDir, group)) {
      logger.debug("Creating group directory: {}", group);
    }
    spritesheet.getData().forEach(info -> processInfo(info, group, spritesheet, outputDir));
  }

  private static void processInfo(
      SpriteInfo info, String group, SpriteSheet spritesheet, String outputDir) {
    ImageUtils.writeImage(
        FileUtils.pathAsString(outputDir, group, info.getFilename()),
        info.extractImage(spritesheet.getImage()));
  }

  private static List<SpriteSheet> processGroups(Map<String, List<File>> groups) {
    return groups.entrySet().stream()
        .map(SpriteSheetUtils::processEntry)
        .collect(Collectors.toList());
  }

  private static SpriteSheet processEntry(Entry<String, List<File>> entry) {
    return new SpriteSheet(
        entry.getKey(),
        ImageUtils.loadImage(findImageFile(entry.getValue())),
        loadAtlas(findAtlasFile(entry.getValue())));
  }

  private static List<SpriteInfo> loadAtlas(File atlasFile) {
    try (Stream<String> stream = Files.lines(atlasFile.toPath(), StandardCharsets.UTF_8)) {
      return stream.skip(1).map(SpriteInfo::parseLine).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("Issue processing atlas file", e);
    }
    return null;
  }

  private static File findImageFile(List<File> files) {
    return CollectionUtils.find(files, file -> isValidFile(file, imagePattern))
        .orElseThrow(() -> new RuntimeException("Image not found"));
  }

  private static File findAtlasFile(List<File> files) {
    return CollectionUtils.find(files, file -> isValidFile(file, atlasPattern))
        .orElseThrow(() -> new RuntimeException("Atlas not found"));
  }

  private static Map<String, List<File>> groupAssetsByCommonName(File[] assets) {
    return CollectionUtils.groupBy(
        Arrays.asList(assets), SpriteSheetUtils::extractGroupName, Function.identity());
  }

  private static String extractGroupName(File file) {
    return RegexUtils.match(file.getName(), groupNamePattern);
  }

  private static boolean isValidFile(File file, Pattern pattern) {
    return RegexUtils.matches(file.getName(), pattern);
  }
}
