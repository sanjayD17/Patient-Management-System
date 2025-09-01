package com.pm.Authentication_Service.controller;



import com.pm.Authentication_Service.dto.LoginRequestDTO;
import com.pm.Authentication_Service.dto.LoginResponseDTO;
import com.pm.Authentication_Service.dto.UserDto;
import com.pm.Authentication_Service.entity.User;
import com.pm.Authentication_Service.service.AuthService;
import com.pm.Authentication_Service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto) {

        // Check if user already exists
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User with this email already exists");
        }

        // Create new user
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole() != null ? userDto.getRole() : "USER"); // default role

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
//    @Operation(summary = "Generate token on user login")
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
//
//        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);
//
//        if (tokenOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(new LoginResponseDTO("Invalid email or password", true));
//        }
//
//        String token = tokenOptional.get();
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }

    //       -------------------- login ---------------------

//    @Operation(summary = "Generate token on user login")
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(
//           @Valid @RequestBody LoginRequestDTO loginRequestDTO) {
//
//        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);
//
//        if (tokenOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("[ Invalid email or password ] " +" [ are you a new user --> go to register ]", true));
//        }
//
//        String token = tokenOptional.get();
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }
@Operation(summary = "Generate token on user login")
@PostMapping("/login")
public ResponseEntity<LoginResponseDTO> login(
        @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

    Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);

    if (tokenOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(LoginResponseDTO.error(
                        "Invalid email or password. Are you a new user? Go to /register"
                ));
    }

    return ResponseEntity.ok(LoginResponseDTO.success(tokenOptional.get()));
}


//         --------------------validate---------------------
//    @Operation(summary = "Validate Token")
//    @GetMapping("/validate")
//    public ResponseEntity<Void> validateToken(
//            @RequestHeader("Authorization") String authHeader) {
//
//        // Authorization: Bearer <token>
//        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        return authService.validateToken(authHeader.substring(7))
//                ? ResponseEntity.ok().build()
//                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//}
@Operation(summary = "Validate Token")
@GetMapping("/validate")
public ResponseEntity<String> validateToken(
       @Valid @RequestHeader("Authorization") String authHeader) {

    // Check if header is missing or invalid
    if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    boolean isValid = authService.validateToken(authHeader.substring(7));
    if (isValid) {
        return ResponseEntity.ok().body("Authorized");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}}