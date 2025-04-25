package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverUtils;
import utils.Utils;

public class Hooks {

    @Before
    public void setup() {
        DriverUtils.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Utils.captureScreenshot(scenario.getName());
        }
        DriverUtils.quitDriver();
    }
}
