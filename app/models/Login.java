package models;

import play.db.ebean.Model;

import play.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Login extends Model{

	private static final Logger.ALogger log = Logger.of(Login.class);

	@Id
	@NotNull
	public User user;


	@NotNull
	public String hash;

	public static Finder<Long, History> find = new Finder<Long,History>(Long.class, History.class);


	public Login() {

	}

}