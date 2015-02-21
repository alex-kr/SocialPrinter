package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Login extends Model{

	@Id
	@NotNull
	@OneToOne(name = "user_id")
	public User user;


	@NotNull
	public String hash;


	public Login() {

	}

}