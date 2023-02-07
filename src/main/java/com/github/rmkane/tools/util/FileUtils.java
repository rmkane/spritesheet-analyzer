package com.github.rmkane.tools.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtils {
  private static final ClassLoader loader = FileUtils.class.getClassLoader();

  public static boolean mkdir(String pathname) {
    File directory = new File(pathname);
    if (!directory.exists()) {
      return directory.mkdirs();
    }
    return false;
  }

  public static File[] listFilesInDirectory(String resourcePath) {
    return getFileFromURL(resourcePath).listFiles();
  }

  public static File getFileFromURL(String resourcePath) {
    URL url = loader.getResource(resourcePath);
    try {
      return new File(url.toURI());
    } catch (URISyntaxException e) {
      return new File(url.getPath());
    }
  }
}
