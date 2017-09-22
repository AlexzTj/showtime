package com.demo.showtime.api;

import com.demo.showtime.api.hackerrank.model.submission.Request;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.controller.SubmissionController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by atujicov on 9/18/2017.
 */
@Component
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    @Autowired
    private RestTemplate restTemplate;
    @Value("${submission.url}")
    private URI submitURI;
    @Value("${submission.format}")
    private String submissionFormat;
    @Value("${api.key}")
    private String key;
    @Value("${submission.wait}")
    private String submissionWait;


    public Response submit(String source, String testCases) {
        logger.debug("Preparing for submit source: {}, testCases:{}", source, testCases);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("source", source);
        map.add("lang", "5");
        map.add("testcases", testCases);
        map.add("api_key", key);
        HttpEntity<MultiValueMap<String, String>> httpReq = new HttpEntity<>(map, headers);
        return restTemplate.postForObject(submitURI, httpReq, Response.class);
    }

    private class SubmissionRequestEnriched implements Serializable {
        private final String source;
        private final Integer lang;
        private final String testcases;
        private final String wait;
        private final String format;
        private final String api_key;

        public SubmissionRequestEnriched(Request request) throws UnsupportedEncodingException {
            source = URLEncoder.encode(request.getSource(), "UTF-8");
            lang = request.getLang();
            testcases = URLEncoder.encode(request.getTestcases(), "UTF-8");
            wait = submissionWait;
            format = submissionFormat;
            api_key = key;
            ;
        }

        public String getSource() {
            return source;
        }

        public Integer getLang() {
            return lang;
        }

        public String getTestcases() {
            return testcases;
        }

        public String getWait() {
            return wait;
        }

        public String getFormat() {
            return format;
        }

        public String getApi_key() {
            return api_key;
        }
    }
}
