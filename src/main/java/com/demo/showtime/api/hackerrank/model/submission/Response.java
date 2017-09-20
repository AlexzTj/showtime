package com.demo.showtime.api.hackerrank.model.submission;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by atujicov on 9/19/2017.
 */
public class Response {
    private Result result;
    @JsonProperty("result")
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("Response {\n");
        sb.append("  result: ").append(result).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
