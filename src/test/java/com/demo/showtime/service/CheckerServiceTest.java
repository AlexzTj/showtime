package com.demo.showtime.service;

import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.api.hackerrank.model.submission.Result;
import com.demo.showtime.model.SubmissionResult;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by atujicov on 9/22/2017.
 */
public class CheckerServiceTest {
    private final CheckerService target = new CheckerService();

    @Test
    public void processResponse() {
        Response response = new Response();
        Result result = new Result();
        result.setMessage(Arrays.asList("Success", "Runtime exception"));
        result.setStderr(Arrays.asList(false, "error"));
        result.setStdout(Arrays.asList("2", ""));
        response.setResult(result);
        SubmissionResult submissionResult = target.processResponse(response, Arrays.asList("1", "2"), Arrays.asList("2", "2"));
        SubmissionResult.Result expectedResult1 = new SubmissionResult.Result();
        expectedResult1.setExpected("2");
        expectedResult1.setStdin("1");
        expectedResult1.setStdout("2");
        SubmissionResult.Result expectedResult2 = new SubmissionResult.Result();
        expectedResult2.setExpected("2");
        expectedResult2.setStdin("2");
        expectedResult2.setStdError("error");
        expectedResult2.setBusinessError("Runtime error");
        SubmissionResult.Result actual1 = submissionResult.getResults().get(0);
        SubmissionResult.Result actual2 = submissionResult.getResults().get(1);
        assertEquals(actual1.toString(), expectedResult1, actual1);
        assertEquals(actual2.toString(), expectedResult2, actual2);
    }

}