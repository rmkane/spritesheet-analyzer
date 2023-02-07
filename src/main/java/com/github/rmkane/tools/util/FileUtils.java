package com.github.rmkane.tools.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
  private static final ClassLoader loader = FileUtils.class.getClassLoader();

  public static boolean mkdir(String pathname) {
    File directory = new File(pathname);
    return !directory.exists() ? directory.mkdirs() : false;
  }

  public static File[] listFilesInDirectory(String resourcePath) {
    return listFilesInDirectory(getFileFromURL(resourcePath));
  }

  public static File getFileFromURL(String resourcePath) {
    return getFileFromURL(loader.getResource(resourcePath));
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
