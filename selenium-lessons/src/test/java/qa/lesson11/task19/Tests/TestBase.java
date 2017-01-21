package qa.lesson11.task19.Tests;

import org.junit.After;
import org.junit.Before;
import qa.lesson11.task19.General.Application;

public class TestBase {

    public Application app = new Application();

    @Before
    public void start() {
    }

    @After
    public void stop() {
       app.quit();
    }
}