package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Printer extends Model {

    private static final Logger.ALogger log = Logger.of(Printer.class);

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String location;

    public String name;

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

    public static List<Printer> getUserPrinters(Long userId) {
        return find.where().eq("user.id", userId).findList();
    }
}
