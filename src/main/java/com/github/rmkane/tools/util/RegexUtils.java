package com.github.rmkane.tools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
  public static String match(String value, Pattern pattern) {
    Matcher matcher = pattern.matcher(value);
    return matcher.find() ? matcher.group() : null;
  }

  public static String match(String value, String pattern, int flags) {
    return match(value, Pattern.compile(pattern, flags));
  }

  public static String match(String value, String pattern) {
    return match(value, pattern, 0);
  }

  public static boolean matches(String value, Pattern pattern) {
    return pattern.matcher(value).matches();
  }
}
