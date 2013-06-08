package helper;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.test.FakeApplication;
import play.test.Helpers;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 * Date: 13. 6. 9.
 * Time: 오전 2:51
 */
public class TestHelper {

    public static FakeApplication app;

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

}
