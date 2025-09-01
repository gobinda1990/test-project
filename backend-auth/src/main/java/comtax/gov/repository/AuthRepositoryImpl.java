package comtax.gov.repository;

import org.springframework.stereotype.Repository;
import comtax.gov.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {
	
	private final Logger logger = LoggerFactory.getLogger(AuthRepositoryImpl.class);
	
	private final JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public User getAuthdetails(User user) throws Exception {
		logger.info("Enter into Auth REpo:---");
		return jdbcTemplate.queryForObject(
	            SQL_AUTH_DET,
	            new Object[]{user.getUsername()},
	            new UserRowMapper()
	        );
	}

}
