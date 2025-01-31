package com.hms.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.config.JwtProvider;
import com.hms.dao.UserDao;
import com.hms.pojos.User_role;
import com.hms.request.LoginRequest;
import com.hms.response.AuthResponse;
import com.hms.services.CustomeUserServiceImplementation;
import com.hms.services.UserService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	

//	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	   
	    private AuthenticationManager authenticationManager;
	
//	    @Autowired
//	    private CustomUserDetailsService userDetailsService;
	    
	    
//	    private PasswordEncoder encoder ;
//	private UserRepository userRepository;
	    private UserDao userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtProvider jwtProvider;
	private CustomeUserServiceImplementation customUserDetails;
//	private CartRepository cartRepository;
//
//    private PasswordResetTokenService passwordResetTokenService;
//
    private UserService userService;

	public AuthController(UserDao userRepository, 
			PasswordEncoder passwordEncoder, 
			JwtProvider jwtProvider,
			CustomeUserServiceImplementation customUserDetails,
			UserService userService
			) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
		this.customUserDetails = customUserDetails;
		this.userService=userService;

	}
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		System.out.println(username + " ----- " + password);

		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();

		authResponse.setMessage("Login Success");
		authResponse.setJwt(token);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


		String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


		authResponse.setRole(User_role.valueOf(roleName));

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

		System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    
//    
//    private PasswordEncoder encoder ;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest  request) {
//        try {
//            logger.info(" Login attempt for email: {}", request.getEmail());
// 
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
//  
//            logger.info("Authentication Successful for: {}", request.getEmail());
//           
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//            String token = jwtService.generateToken(userDetails);
//
//            JwtResponse response = JwtResponse.builder()
//                    .jwtToken(token)
//                    .username(userDetails.getUsername())
//                    .build();
//
//            logger.info("Token generated successfully!");
//
//            return ResponseEntity.ok(response);
//
//        } catch (BadCredentialsException e) {
//            logger.error(" Invalid username or password for email: {}", request.getEmail());
//            
//            System.out.println(e);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//            
//        } catch (Exception e) {
//            logger.error(" Unexpected error during login: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
//        }
//    }
//}
