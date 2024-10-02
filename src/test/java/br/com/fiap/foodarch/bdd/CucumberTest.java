package br.com.fiap.foodarch.bdd;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@IncludeClassNamePatterns(".*Definitions")
@IncludeTags("not @ignore")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "features")
public class CucumberTest {
}
