package comtax.gov.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import comtax.gov.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(
	            rs.getString("userid"),
	            rs.getString("user_password")	          
	        );
	}

}
