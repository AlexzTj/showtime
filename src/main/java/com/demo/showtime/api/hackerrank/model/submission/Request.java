package com.demo.showtime.api.hackerrank.model.submission;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by atujicov on 9/18/2017.
 */
public class Request implements Serializable {
    @NotEmpty
    private String source;
    @NotEmpty
    private Integer lang;
    @NotEmpty
    private String testcases;
    private String callback_url;
    private String wait;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getLang() {
        return lang;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }

    public String getTestcases() {
        return testcases;
    }

    public void setTestcases(String testcases) {
        this.testcases = testcases;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }
}
