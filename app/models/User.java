package models;

import org.hibernate.validator.constraints.Length;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
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

	@NotNull
	public String email;

    @NotNull
    public String password;

	public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);

	public User() {

	}

    public static User authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }

}