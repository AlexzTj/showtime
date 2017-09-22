package com.demo.showtime.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atujicov on 9/21/2017.
 */
public class SubmissionResult {
    private CompilationError compilationError;
    private List<Result> results = new ArrayList<>();

    public CompilationError getCompilationError() {
        return compilationError;
    }

    public void setCompilationError(CompilationError compilationError) {
        this.compilationError = compilationError;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class CompilationError {
        private String message;
        private Integer code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }

    public static class Result {
        private String stdin;
        private String stdout;
        private String expected;
        private String stdError;
        private String businessError;

        public String getStdin() {
            return stdin;
        }

        public void setStdin(String stdin) {
            this.stdin = stdin;
        }

        public String getStdout() {
            return stdout;
        }

        public void setStdout(String stdout) {
            this.stdout = stdout;
        }

        public String getExpected() {
            return expected;
        }

        public void setExpected(String expected) {
            this.expected = expected;
        }

        public String getStdError() {
            return stdError;
        }

        public void setStdError(String stdError) {
            this.stdError = stdError;
        }

        public void setBusinessError(String businessError) {
            this.businessError = businessError;
        }

        public String getBusinessError() {
            return businessError;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Result result = (Result) o;

            if (!getStdin().equals(result.getStdin())) return false;
            if (getStdout() != null ? !getStdout().equals(result.getStdout()) : result.getStdout() != null)
                return false;
            if (!getExpected().equals(result.getExpected())) return false;
            if (getStdError() != null ? !getStdError().equals(result.getStdError()) : result.getStdError() != null)
                return false;
            return getBusinessError() != null ? getBusinessError().equals(result.getBusinessError()) : result.getBusinessError() == null;
        }

        @Override
        public int hashCode() {
            int result = getStdin().hashCode();
            result = 31 * result + (getStdout() != null ? getStdout().hashCode() : 0);
            result = 31 * result + getExpected().hashCode();
            result = 31 * result + (getStdError() != null ? getStdError().hashCode() : 0);
            result = 31 * result + (getBusinessError() != null ? getBusinessError().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "stdin='" + stdin + '\'' +
                    ", stdout='" + stdout + '\'' +
                    ", expected='" + expected + '\'' +
                    ", stdError='" + stdError + '\'' +
                    ", businessError='" + businessError + '\'' +
                    '}';
        }
    }

}
