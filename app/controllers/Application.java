package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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
//        	session("email", loginForm.get().email);
            User user = User.findByEmail(loginForm.get().email);
            session("userId", String.valueOf(user.id));
        	return redirect(routes.Application.printers());
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

    public static Result printers() {
        List<Printer> printer = Printer.getAll();
        return ok(printers.render(printer));
    }

    public static Result specifications(Long printerId, Long printerSpecificationId) {
        PrinterSpecification printerSpecification = PrinterSpecification.find.byId(printerSpecificationId);
        Printer printer = Printer.find.byId(printerId);
        return ok(specification.render(printer, printerSpecification));
    }

    public static Result addMoney(Long userId) {
        User user = User.findById(userId);
        user.balance += 10;
        user.update();
        return ok(profile.render(user));
    }

    public static Result showUserPrinters() {
        List<String> printerNames = Driver.definePrintersNames();
        return ok(showUserPrinters.render(printerNames));
    }

    public static Result addPrinter(String p) {
        Long userId = Long.parseLong(session("userId"));
        User user = User.findById(userId);
        Printer printer = new Printer();
        printer.name = p;
        printer.location = "Paris";
        printer.printerSpecification = Driver.getSpecificationByName(p);
        printer.user = User.findById(userId);
        printer.save();
        return ok(profile.render(user));
    }

    public static Result loadFile(Long printerId) {
        if (session("userId") != null) {
            Http.MultipartFormData body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart userFile = body.getFile("userFile");
            if (userFile != null) {
                String fileName = userFile.getFilename();
                File file = userFile.getFile();
                /// Save
                Document doc = new Document();
                doc.date_loaded = new Date();
                doc.name = "eXample";
                doc.visibility = true;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
                System.out.println(session("userId"));
                doc.user = User.findById(Long.parseLong(session("userId")));
                System.out.println("User name: " + doc.user.name);
//                System.out.println("user: " + doc.user.name);
//                System.out.println("path: " + );
                Path path = Paths.get(fileName);
//                doc.data = null;
//                try {
////                    doc.data = Files.readAllBytes(path);
//                    FileInputStream fis = new FileInputStream(file);
//
//                    List<Byte> list = new ArrayList<>();
//                    int a;
//                    do {
//                        a = fis.read();
//                        if (a > 0 ) {
//                            list.add((byte)a);
//                        }
//                    } while (a > 0);
//                    doc.data = new byte[list.size()];
//                    for (int i = 0; i < list.size(); i++) {
//                        doc.data[i] = list.get(i);
//                    }
//                    doc.save();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                ///
                File newFile = new File(play.Play.application().path().toString() + "//public//uploads//" + "_" + fileName);
                file.renameTo(newFile); //here you are moving photo to new directory
//                System.out.println(newFile.getPath()); //this path you can store in database
                doc.path = newFile.getPath();
                doc.save();

                History history = new History();
                history.datePrint = new Date();
                history.printer = Printer.find.byId(printerId);
                history.user = User.findById(Long.parseLong(session("userId")));
                history.document = doc;
                history.save();
                Driver.documentSend(doc);
            }
            return ok();
        } else {
            return ok(login.render(form(Login.class)));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result documentReceived() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("key1").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("IT'S OKAY!!!!!!!!!!!!1");
        }
    }
}
