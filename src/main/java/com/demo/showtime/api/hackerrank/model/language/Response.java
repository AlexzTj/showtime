package com.demo.showtime.api.hackerrank.model.language;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Response {
  /* Languages */
  private Languages languages = null;
  @JsonProperty("languages")
  public Languages getLanguages() {
    return languages;
  }
  public void setLanguages(Languages languages) {
    this.languages = languages;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    sb.append("  languages: ").append(languages).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

