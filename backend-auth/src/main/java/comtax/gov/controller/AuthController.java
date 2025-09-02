package comtax.gov.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comtax.gov.model.User;
import comtax.gov.service.AuthService;
import comtax.gov.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://10.153.43.8", allowCredentials = "true")

public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private final JwtUtil jwtUtil;
	
	private final AuthService authService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User request) throws Exception {
		logger.info("Enter into Auth Controller:--");
	    if ("GSTG2G19".equals(request.getUsername()) && "password".equals(request.getPassword())) {
	    	request=authService.getAuthdetails(request);
	    	logger.info("USER IS:"+request.getUsername());
	        String token = jwtUtil.generateToken(request.getUsername());
	        return ResponseEntity.ok(Map.of("token", token));
	    }
	    return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
	}
}
