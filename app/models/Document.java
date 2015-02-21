package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import play.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Date;

import java.util.List;

@Entity
public class Document extends Model{ 

	private static final Logger.ALogger log = Logger.of(Document.class);

	@Id
	@NotNull
	public Long id;

	@NotNull
	public String name;

	@NotNull
	public boolean visibility;

	@NotNull
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public java.util.Date date_loaded = new Date(); 

	@Lob
	@NotNull
	public byte[] data;

	public static Finder<Long, Document> find = new Finder<Long,Document>(Long.class, Document.class);

	public Document() {

	}

	public static int count() {
        return find.findRowCount();
    }

    public static Document findById(Long id) {
        return Document.find.byId(id);
    }

    public static Document findByName(String name) {
        return Document.find.where().eq("name", name).findUnique();
    }

    public static List<Document> getDocumentList() {
    	return Document.find.all();
    }



}