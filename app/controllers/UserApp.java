package controllers;

import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user.editform;
import views.html.user.login;
import views.html.user.outform;
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

    public static Result outForm(){
        return ok(outform.render());
    }

    public static Result login(){

        Form<User> userForm = form(User.class).bindFromRequest();

        if(userForm.hasErrors()){
            flash("message","로그인에 실패하였습니다.");
        }

        User loginUser = User.findByUser(userForm.get());

        if(loginUser != null){
            flash("message", "로그인에 성공하였습니다");
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

    public static Result signout(){

        Form<User> userForm = form(User.class).bindFromRequest();

        User user = User.findByUserId(userForm.get().userId);

        if(userForm.get().password.equals(user.password)){
            User.removeUser(user);
            flash("message","탈퇴하였습니다");
            return redirect(routes.Application.index());
        }

        flash("message","다시 확인해주세요");
        return redirect(routes.Application.index());

    }

    public static Result editUserForm(){

        User user = User.findByUserId(session().get("userId"));

        if(user != null){
            return ok(editform.render(user));
        }

        return redirect(routes.Application.index());

    }

    public static Result editUser(){
        Form<User> userForm = form(User.class).bindFromRequest();

        if(userForm.hasErrors()){
            flash("message","회원 정보 수정에 실패하였습니다.");
        }

        User user = new User();

        String email = userForm.data().get("email");
        String name = userForm.data().get("userName");

        if(email != null && name != null){
            user.userId = session().get("userId");
            user.email = email;
            user.userName = name;

            User.editUser(user);
            flash("message","회원정보를 수정하였습니다");
        } else {
            flash("message","회원 정보 수정에 실패하였습니다.");
        }

        return redirect(routes.Application.index());
    }

    public static Result duplicateId(){
        String flag = "false";

        DynamicForm requestData = form().bindFromRequest();

        User user = User.findByUserId(requestData.get("userId"));

        if(user==null){
            flag = "true";
        }

        return ok(flag);
    }



}
