package com.demo.showtime.api;

import com.demo.showtime.api.hackerrank.model.submission.Request;
import com.demo.showtime.api.hackerrank.model.submission.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Created by atujicov on 9/18/2017.
 */
@Component
public class Client {

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

    public Response submit(Request request) {
//        SubmissionRequestEnriched enriched = null;
//        try {
//            enriched = new SubmissionRequestEnriched(request);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("source", "print 1");
        map.add("lang", "5");
        map.add("testcases", "[\"1\"]");
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
            source = URLEncoder.encode(request.getSource(),"UTF-8");
            lang = request.getLang();
            testcases = URLEncoder.encode(request.getTestcases(),"UTF-8");
            wait = submissionWait;
            format = submissionFormat;
            api_key = key;;
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
