package SpringSecurity.Controller;



import SpringSecurity.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;

import SpringSecurity.DTO.ApiResponse;
import SpringSecurity.DTO.LoginRequest;
import SpringSecurity.DTO.SignupRequest;
import SpringSecurity.Service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager, AuthService authService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    // SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest request) {
        userService.register(request);
        return ResponseEntity.ok(new ApiResponse("User registered successfully"));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @RequestBody LoginRequest request) {
        System.out.println("Login request: " + request);
        return authService.login(request);

    }
}
