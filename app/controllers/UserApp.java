package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.login;
import views.html.user.register;

import static play.data.Form.form;

/**
 * Created with IntelliJ IDEA.
 * User: Changhwa Oh ( changhwaoh.co@gmail.com )
 * Date: 13. 6. 15.
 * Time: 오전 9:18
 */
public class UserApp  extends Controller {

    public static Result loginForm() {
        return ok(login.render());
    }

    public static Result registerForm(){
        return ok(register.render());
    }

    public static Result login(){

        Form<User> userForm = form(User.class).bindFromRequest();

        if(userForm.hasErrors()){
            flash("message","로그인에 실패하였습니다.");
        }

        User loginUser = User.findByUser(userForm.get());

        if(loginUser != null){
            flash("message","로그인에 성공하였습니다");
            session("userId",loginUser.userId);
            session("userName",loginUser.userName);
            session("email", loginUser.email);
        } else {
            flash("message","로그인에 실패하였습니다");
        }

        return redirect(routes.Application.index());
    }

    public static Result logout(){
        session().clear();
        return redirect(routes.Application.index());
    }

    public static Result createUser(){
        Form<User> userForm = form(User.class).bindFromRequest();

        if(userForm.hasErrors()){
            flash("message","회원가입에 실패하였습니다.");
        }

        String userId = User.createUser(userForm.get());

        if(userId != ""){
            flash("message","회원가입을 축하드립니다.");
        } else {
            flash("message","회원가입에 실패하였습니다.");
        }

        return redirect(routes.Application.index());
    }

}
