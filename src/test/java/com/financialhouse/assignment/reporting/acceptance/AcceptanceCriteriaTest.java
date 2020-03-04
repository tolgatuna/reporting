package com.financialhouse.assignment.reporting.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/features",
        glue = "com.financialhouse.assignment.reporting.acceptance.glue"
)
public class AcceptanceCriteriaTest {

}
