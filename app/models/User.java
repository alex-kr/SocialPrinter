package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model{
	
	@Id
	@NotNull
	public Long id;
	
	@Length(max=255)
	@NotNull
	public String name;
	
	@NotNull
	public double balance;
	
	@NotNull
	public int rating;

	@Length(max=100)
	@NotNull
	@OneToOne()
	public String login;

	public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);

	public User() {

	} 

}