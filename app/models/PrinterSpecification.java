package models;

import play.db.ebean.Model;

import play.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PrinterSpecification extends Model {

    private static final Logger.ALogger log = Logger.of(PrinterSpecification.class);

    @Id
    @NotNull
    public Long id;

    public boolean color;

    public boolean photo;

    @Column(name = "min_length")
    public int minLength;

    @Column(name = "max_length")
    public int maxLength;

    @Column(name = "min_width")
    public int minWidth;

    @Column(name = "max_width")
    public int maxWidth;

    @Column(name = "ppm_color")
    public int ppmColor;

    @Column(name = "ppm_black")
    public int ppmBlack;

    @Column(name = "min_length_photo")
    public int minLengthPhoto;

    @Column(name = "max_length_photo")
    public int maxLengthPhoto;

    @Column(name = "min_width_photo")
    public int minWidthPhoto;

    @Column(name = "max_width_photo")
    public int maxWidthPhoto;

    public static Finder<Long, PrinterSpecification> find = new Finder<>(Long.class, PrinterSpecification.class);

}
