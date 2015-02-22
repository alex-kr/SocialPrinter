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
            User user = User.findByEmail(session("email"));
        	return redirect(routes.Application.profile(user.id));
    	}    	
    }

    public static Result registration() {
        return ok(registration.render(form(Registration.class)));
    }

    public static Result registerUser() {
        Form<Registration> regForm = form(Registration.class).bindFromRequest();
        if (regForm.hasErrors()) {
            return badRequest(registration.render(regForm));
        } else {
            session().clear();
            session("email", regForm.get().email);
            User user = new User();
            user.balance = 0;
            user.rating = 0;
            user.email = regForm.get().email;
            user.name = regForm.get().name;
            user.password = regForm.get().password;
            user.save();
            return redirect(routes.Application.profile(user.id));
        }
    }

    public static Result profile(Long userId) {
        User user = User.find.byId(userId);
        return ok(profile.render(user));
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

    public static class Registration {
        public String name;
        public String email;
        public String password;

        public String validate() {
            if (name.equals("") || name == null) {
                return "Empty user";
            }
            if (User.findByEmail(email) != null) {
                return "User already exist";
            }
            return null;
        }
    }
}
