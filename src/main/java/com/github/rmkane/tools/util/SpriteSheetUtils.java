package com.github.rmkane.tools.util;

import com.github.rmkane.tools.domain.SpriteInfo;
import com.github.rmkane.tools.domain.SpriteSheet;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpriteSheetUtils {
  private static final Logger logger = LoggerFactory.getLogger(SpriteSheetUtils.class);

  private static final String OUTPUT_DIR = "output";

  private static final Pattern groupNamePattern;
  private static final Pattern imagePattern;
  private static final Pattern atlasPattern;

  static {
    groupNamePattern = Pattern.compile("^[a-z0-9]+", Pattern.CASE_INSENSITIVE);
    imagePattern = Pattern.compile("^[a-z0-9]+\\.png", Pattern.CASE_INSENSITIVE);
    atlasPattern = Pattern.compile("^[a-z0-9]+_atlas\\.txt", Pattern.CASE_INSENSITIVE);
  }

  public static List<SpriteSheet> loadSpritesheets(String resourcePath) {
    Map<String, List<File>> groups =
        groupAssetsByCommonName(FileUtils.listFilesInDirectory(resourcePath));
    if (!isMapValid(groups)) {
      logger.error("Invalid asset structure");
      throw new RuntimeException("Invalid asset structure");
    }
    return processGroups(groups);
  }

  public static void processSpritesheets(List<SpriteSheet> spritesheets) {
    spritesheets.stream().forEach(SpriteSheetUtils::processSpritesheet);
  }

  private static void processSpritesheet(SpriteSheet spritesheet) {
    String groupName = spritesheet.getName();
    FileUtils.mkdir(Paths.get(OUTPUT_DIR, groupName).toString());
    for (SpriteInfo info : spritesheet.getData()) {
      ImageUtils.writeImage(
          Paths.get(OUTPUT_DIR, groupName, info.getFilename()).toString(),
          ImageUtils.extractSubImage(
              spritesheet.getImage(),
              info.getStartX(),
              info.getStartY(),
              info.getWidth(),
              info.getHeight()));
    }
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
    return files.stream()
        .filter(file -> imagePattern.matcher(file.getName()).matches())
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Image not found"));
  }

  private static File findAtlasFile(List<File> files) {
    return files.stream()
        .filter(file -> atlasPattern.matcher(file.getName()).matches())
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Atlas not found"));
  }

  private static <K, V> boolean isMapValid(Map<K, List<V>> map) {
    if (map == null) return false;
    if (map.size() < 1) return false;
    Iterator<Entry<K, List<V>>> it = map.entrySet().iterator();
    while (it.hasNext()) {
      if (it.next().getValue().size() < 2) {
        return false;
      }
    }
    return true;
  }

  private static Map<String, List<File>> groupAssetsByCommonName(File[] assets) {
    Map<String, List<File>> groups = new HashMap<>();
    for (File file : assets) {
      String groupName = extractGroupName(file.getName());
      List<File> files = groups.getOrDefault(groupName, new ArrayList<File>());
      files.add(file);
      groups.put(groupName, files);
    }
    return groups;
  }

  private static String extractGroupName(String filename) {
    Matcher matcher = groupNamePattern.matcher(filename);
    return matcher.find() ? matcher.group() : null;
  }
}
