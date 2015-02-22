package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Document;
import models.PrinterSpecification;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

public class Driver extends Controller {
    public static List<String> definePrintersNames() {
        List<String> names = new ArrayList<>();
        names.add("Samsung");
        names.add("LG");
        names.add("HP");
        return names;
    }

    public static PrinterSpecification getSpecificationByName(String name) {
        PrinterSpecification printerSpecification = new PrinterSpecification();
        printerSpecification.color = true;
        printerSpecification.photo = false;
        return printerSpecification;
    }

    public static String documentSend(Document document) {
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        JsonNode json = Json.newObject()
                .put("key1", "OK");

        String url = "http://localhost:9000/documentReceived";
        WSRequestHolder holder = WS.url(url);
        WS.url(url).setTimeout(5000).setContentType("application/json").post(json);
        return "OK";
    }
}
