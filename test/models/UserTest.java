package models;


import helper.TestHelper;
import org.junit.Before;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 */
public class UserTest extends TestHelper {

    private User testUser;

    @Before
    public void setUp(){
        // String userId, String userName, String email, String password, String profileImg
        testUser = new User("test1","홍길동","test1@narratage.com","xptmxm1`","");
    }

    @Test
    public void createUser(){
        //given

        //when
        String dummyUserId = User.createUser(testUser);

        //then
        assertThat(testUser.userId).isEqualTo(dummyUserId);

    }


}
