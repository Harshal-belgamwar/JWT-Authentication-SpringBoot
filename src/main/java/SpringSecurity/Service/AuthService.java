package SpringSecurity.Service;





import SpringSecurity.DTO.ApiResponse;

import SpringSecurity.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SpringSecurity.DTO.LoginRequest;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ApiResponse> login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        if(authentication.isAuthenticated()) {
            //User user= userRepository.findByUsername(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("Username not found"));
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = jwtService.generateJwtToken(customUserDetails.getUser());
            return ResponseEntity.ok(new ApiResponse("JWT Token :"+jwt));

        }else {
            throw new UsernameNotFoundException("Invalid username or password");
        }


    }
}

