package feature.user;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.hascode.tutorial.App;
import com.intuit.karate.junit4.Karate;

@RunWith(Karate.class)
public class UserManagementTest {
    private static App app;

    @BeforeClass
    public static void setupClass() throws Exception {
        app = new App();
        app.start();
    }

    @AfterClass
    public static void teardownClass() throws Exception {
        app.stop();
    }
}
