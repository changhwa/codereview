package controller;

import controllers.routes;
import helper.TestHelper;
import org.junit.Before;
import org.junit.Test;
import play.mvc.*;

import java.util.HashMap;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 */
public class UserAppTest extends TestHelper{

    @Test
    public void testLoginFormView() {

        Result result = callAction(routes.ref.UserApp.loginForm());
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void testRegisterFormView() {

        Result result = callAction(routes.ref.UserApp.registerForm());
        assertThat(status(result)).isEqualTo(OK);
    }
}
