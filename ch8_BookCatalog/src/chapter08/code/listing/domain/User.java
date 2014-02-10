package chapter08.code.listing.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import chapter08.code.listing.utils.Unique;

/**
 * Book Domain object which defines properties of a book in the catalog.
 * 
 * @author asarin
 */
public class User {

	private String fname;
	private String mname;
	private String lname;
	private String email;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String fname, String mname, String lname, String email) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.email = email;
	}

	public User() {
		super();
	}

	@Override
	public boolean equals(Object otherObject) {
		User otherUser = (User) otherObject;
		if (otherUser.getEmail() == this.getEmail()) {
			return true;
		} else {
			return false;
		}
	}
}
