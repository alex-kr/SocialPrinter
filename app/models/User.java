package models;

import org.hibernate.validator.constraints.Length;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

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