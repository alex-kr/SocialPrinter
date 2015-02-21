package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

/*
* Used for home page operations. Login/registration etc.
* Top menu buttons
*
*/

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
