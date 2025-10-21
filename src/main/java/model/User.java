package model;

import java.io.Serializable;

public class User implements Serializable{
	    
		private int id;
		private String name;
		private String email;
		private String pass;
		private String confirmPass;
		
		public User() {}
		
	

		public User(String email,String pass){
			this.email = email;
			this.pass = pass;
		}
		
		public User(String name,String email, String pass) {
			this.name = name;
			this.email = email;
			this.pass = pass;
		}
		public User(int id,String name,String email, String pass) {
			this.id=id;
			this.name = name;
			this.email = email;
			this.pass = pass;
		}
		
		public User(String name,String email, String pass,String confirmPass) {
			this.name = name;
			this.email = email;
			this.pass = pass;
			this.confirmPass=confirmPass;
		}
		
		

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getConfirmPass() {
			return confirmPass;
		}
		public void setConfirmPass(String confirmPass) {
			this.confirmPass = confirmPass;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		
}
