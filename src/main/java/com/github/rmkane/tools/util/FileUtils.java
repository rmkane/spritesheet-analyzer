package com.github.rmkane.tools.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
  private static final ClassLoader loader = FileUtils.class.getClassLoader();

  public static String pathAsString(String path, String... paths) {
    return Paths.get(path, paths).toString();
  }

  public static boolean mkdir(String path, String... paths) {
    return mkdir(pathAsString(path, paths));
  }

  public static boolean mkdir(String pathname) {
    return mkdir(new File(pathname));
  }

  public static boolean mkdir(File directory) {
    return !exists(directory) && directory.mkdirs();
  }

  public static boolean exists(String resourcePath) {
    return exists(getFileFromURL(resourcePath));
  }

  public static boolean exists(File file) {
    return file != null && file.exists();
  }

  public static File getFileFromURL(String resourcePath) {
    return getFileFromURL(loader.getResource(resourcePath));
  }

  public static File[] listFilesInDirectory(String resourcePath) {
    return listFilesInDirectory(getFileFromURL(resourcePath));
  }

  private static File[] listFilesInDirectory(File directory) {
    try {
      return directory.listFiles();
    } catch (Exception e) {
      logger.error("Could not list files", e);
    }
    return null;
  }

  private static File getFileFromURL(URL url) {
    try {
      return new File(url.toURI());
    } catch (URISyntaxException e) {
      return new File(url.getPath());
    }
  }
}
