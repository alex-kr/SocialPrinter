package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Printer extends Model {
    @Id
    @NotNull
    public Long id;

    public String location;

    @Column(name = "pages_available")
    public int pagesAvailable;

    @ManyToOne
    public User user;

    @Column(name = "printer_specification")
    @ManyToOne
    public PrinterSpecification printerSpecification;

    public static Finder<Long, Printer> find = new Finder<>(Long.class, Printer.class);

    public Printer() {

    }
}
