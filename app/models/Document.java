package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Document extends Model{ 

	@Id
	@NotNull
	public Long id;

	@NotNull
	public String name;

	@NotNull
	@Column(name = "public")
	public boolean visibility;

	@NotNull
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public java.util.Date date_loaded = new Date(); 

	@Lob
	@NotNull
	public byte[] data;

	public Document() {

	}

}