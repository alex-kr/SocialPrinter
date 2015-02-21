package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Login extends Model{

	@Id
	@NotNull
	public User user;


	@NotNull
	public String hash;


	public Login() {

	}

}