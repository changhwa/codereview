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

    @Test
    public void login(){

        //given test2사용자가 가입되어 있음
        User dummyUser = new User();
        dummyUser.userId = "test2";
        dummyUser.password = "test2!";
        dummyUser.status = "Y";


        //when test2 사용자가 로그인을 시도함
        User loginUser = User.findByUser(dummyUser);

        //then 로그인 결과 유저정보를 받아옴
        assertThat(loginUser.email).isEqualTo("test2@narratage.com");
        assertThat(loginUser.userName).isEqualTo("황정민");


    }

    @Test
    public void removeUser(){

        //given test2사용자가 가입되어 있음.
        User dummyUser = new User();
        dummyUser.userId = "test2";
        dummyUser.password = "test2!";
        dummyUser.status = "Y";

        //when test2 사용자가 탈퇴를 하려고함
        User.removeUser(dummyUser);

        //then 탈퇴결과를 확인함
        User findUser = User.findByUserId(dummyUser.userId);
        assertThat(findUser.userId).isEqualTo(dummyUser.userId);
        assertThat(findUser.status).isEqualTo("N");
    }


}
