package comtax.gov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import comtax.gov.model.User;
import comtax.gov.repository.AuthRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	private final AuthRepository authRepository;

	@Override
	public User getAuthdetails(User user) throws Exception {
		
		logger.info("Enter into auth service:--");
		return authRepository.getAuthdetails(user);
		
	}

}
