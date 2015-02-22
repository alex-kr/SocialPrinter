package controllers;

import models.PrinterSpecification;
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
}
