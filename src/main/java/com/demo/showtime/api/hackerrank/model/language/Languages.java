package com.demo.showtime.api.hackerrank.model.language;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Languages {
  /* Codes */
  private Codes codes = null;
  /* Names */
  private Names names = null;
  @JsonProperty("codes")
  public Codes getCodes() {
    return codes;
  }
  public void setCodes(Codes codes) {
    this.codes = codes;
  }

  @JsonProperty("names")
  public Names getNames() {
    return names;
  }
  public void setNames(Names names) {
    this.names = names;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Languages {\n");
    sb.append("  codes: ").append(codes).append("\n");
    sb.append("  names: ").append(names).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

