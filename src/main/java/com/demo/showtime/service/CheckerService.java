package com.demo.showtime.service;

import com.demo.showtime.api.hackerrank.model.submission.Response;
import com.demo.showtime.api.hackerrank.model.submission.Result;
import com.demo.showtime.model.SubmissionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * Created by atujicov on 9/21/2017.
 */
@Service
public class CheckerService {
    private static final Logger logger = LoggerFactory.getLogger(CheckerService.class);
    private final String SUCCESS = "Success";

    public SubmissionResult processResponse(Response response, List<String> inputs, List<String> expectedOutputs) {
        logger.debug("Submission response received {}", response);

        Result result = response.getResult();
        if (!StringUtils.isEmpty(result.getCompilemessage())) {
            return handleCompilationError(result);
        }
        final SubmissionResult submissionResult = new SubmissionResult();
        List<String> messages = result.getMessage();
        List<String> stdout = result.getStdout();
        int i = 0;
        for (String msg : messages) {
            SubmissionResult.Result result0 = new SubmissionResult.Result();
            result0.setStdin(inputs.get(i));
            String expectedOutput = expectedOutputs.get(i);
            result0.setExpected(expectedOutput);

            if (SUCCESS.equals(msg)) {
                String actualOutput = stdout.get(i);
                result0.setStdout(actualOutput);
                if (!expectedOutput.equals(actualOutput)) {
                    result0.setBusinessError("Wrong Answer!");
                } else {
                    submissionResult.setValid(true);
                }
            } else {
                result0.setStdError((String) result.getStderr().get(i));
                result0.setBusinessError("Runtime error");
            }
            submissionResult.getResults().add(result0);
            i++;
        }
        logger.debug("Response processed {}", submissionResult);
        return submissionResult;
    }

    private SubmissionResult handleCompilationError(Result result) {
        SubmissionResult submissionResult = new SubmissionResult();
        SubmissionResult.CompilationError cerror = new SubmissionResult.CompilationError();
        cerror.setMessage(result.getCompilemessage());
        cerror.setCode(result.getResult());

        submissionResult.setCompilationError(cerror);
        return submissionResult;
    }
}
