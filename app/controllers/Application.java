package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.profile;
import views.html.registration;

import static play.data.Form.form;

/*
* Used for home page operations. Login/registration etc.
* Top menu buttons
*
*/

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result login() {
        return ok(login.render(form(Login.class)));
    }


    public static Result authenticate() {
    	Form<Login> loginForm = form(Login.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
        	return badRequest(login.render(loginForm));
    	} else {
        	session().clear();
        	session("email", loginForm.get().email);
        	return redirect(routes.Application.profile());
    	}    	
    }

    public static Result registration() {
        return ok(registration.render());
    }

    public static Result profile() {
        return ok(profile.render());
    }

    public static class Login {
        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }
}
