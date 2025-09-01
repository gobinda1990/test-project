package comtax.gov.repository;

import comtax.gov.model.User;

public interface AuthRepository {
	
	public static final String SQL_AUTH_DET = "SELECT * FROM gst_authtoken_details WHERE userid = ?";
	
	public User getAuthdetails(User user)throws Exception;

}
