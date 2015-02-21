package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import play.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class History extends Model {

    private static final Logger.ALogger log = Logger.of(History.class);

    @Id
    @NotNull
    public Long id;

    @ManyToOne
    public User user;

    @ManyToOne
    public Printer printer;

    @ManyToOne
    public Document document;

    @Column(name = "date_print")
    @ManyToOne
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date datePrint = new Date();

    public static Finder<Long, History> find = new Finder<Long,History>(Long.class, History.class);

    public History() {

    }

}
