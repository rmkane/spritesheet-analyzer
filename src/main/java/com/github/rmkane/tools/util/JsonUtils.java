package com.github.rmkane.tools.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
  private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

  public static final ObjectMapper mapper = new ObjectMapper();

  public static <D> String toJson(D data) {
    return toJson(data, false);
  }

  public static <D> String toJson(D data, boolean prettyPrint) {
    try {
      return prettyPrint
          ? mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data)
          : mapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      logger.error("JSON processing error", e);
    }
    return null;
  }

  public static <C> C loadJson(String resourceName, Class<C> clazz) {
    File jsonFile = FileUtils.getFileFromURL(resourceName);

    try {
      return mapper.readValue(jsonFile, clazz);
    } catch (StreamReadException e) {
      logger.error("Stream error", e);
    } catch (DatabindException e) {
      logger.error("Data bind error", e);
    } catch (IOException e) {
      logger.error("Input error", e);
    }

    return null;
  }
}
