package models;

import org.hibernate.validator.constraints.Length;
import play.db.ebean.Model;

import play.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;

@Entity
public class User extends Model{

	private static final Logger.ALogger log = Logger.of(User.class);
	
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

    public static int count() {
        return find.findRowCount();
    }

    public static User findById(Long id) {
        return User.find.byId(id);
    }

    public static User findByName(String name) {
        return User.find.where().eq("name", name).findUnique();
    }

    public static User findByEmail(String email) {
        return User.find.where().eq("email", email).findUnique();
    }

    public static List<User> getUsersList() {
    	return User.find.all();
    }


}