package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Printer extends Model {

    private static final Logger.ALogger log = Logger.of(Printer.class);

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

    public static List<Printer> getAll() {
        return find.all();
    }
}
