package com.demo.showtime.converter;

import com.demo.showtime.model.TestCase;
import com.demo.showtime.model.TestCaseDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by atujicov on 9/20/2017.
 */
public final class TestCaseConverter {
    private TestCaseConverter() {
    }

    public static TestCase convertDTOToEntity(TestCaseDTO testCaseDTO) {
        TestCase tc0 = new TestCase();
        tc0.setId(testCaseDTO.getId());
        tc0.setOutput(testCaseDTO.getOutput());
        tc0.setInput(testCaseDTO.getInput());
        return tc0;
    }
}
