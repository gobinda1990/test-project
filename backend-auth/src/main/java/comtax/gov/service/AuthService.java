package comtax.gov.service;

import comtax.gov.model.User;

public interface AuthService {
	
	public User getAuthdetails(User user)throws Exception;


}
