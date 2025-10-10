package model;
import java.io.Serializable;

public class LoginUser implements Serializable {
	private String email;
	private String pass;
	
	public LoginUser() {}
	public LoginUser(String email,String pass) {
		this.email = email;
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
