package service;

import dao.UserDAO;

public class UserLogic {

	public boolean withdrawUser(int userId) {
		 
		UserDAO dao= new UserDAO();
		
		return dao.delete(userId);
	}
}
