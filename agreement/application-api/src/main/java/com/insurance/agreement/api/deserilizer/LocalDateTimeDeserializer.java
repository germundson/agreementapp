package com.insurance.agreement.api.deserilizer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {
    return LocalDateTime.parse(arg0.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}