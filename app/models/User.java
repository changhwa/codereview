package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 */

@Entity
public class User extends Model {

    @Id
    public String userId;
    public String userName;
    public String email;
    public String password;
    public String profileImg;
    public String grade; // 0 : 관리자,  1: 일반
    public String status;

    @Transient
    private String confirm;


    public static Model.Finder<String, User> find = new Finder<String, User>(String.class, User.class);

    public User(){}

    public User(String userId, String userName, String email, String password, String profileImg){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profileImg = profileImg;
        this.status = "Y";
        this.grade = "1";
    }


    public static String createUser(User user){
        user.save();
        return user.userId;
    }

    public static User findByUser(User user){

        User findUser = find.where()
                .eq("user_id", user.userId)
                .eq("password", user.password)
                .eq("status", "Y")
                .findUnique();

        return findUser;
    }

    public static User removeUser(User user){

        user.status = "N";
        user.update();

        return user;

    }

    public static User findByUserId(String userId){

        User findUser = find.where()
                .eq("userId", userId)
                .findUnique();

        return findUser;

    }

    public static User editUser(User user){
        user.update();
        return user;
    }




}
