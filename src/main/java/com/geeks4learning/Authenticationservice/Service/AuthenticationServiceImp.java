package com.geeks4learning.Authenticationservice.Service;

import com.geeks4learning.Authenticationservice.Config.JwtService;
import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RegisterRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;
import com.geeks4learning.Authenticationservice.Dto.Response.RegistrationResponse;
import com.geeks4learning.Authenticationservice.Enum.Role;
import com.geeks4learning.Authenticationservice.Model.User;
import com.geeks4learning.Authenticationservice.Repository.UserRepository;
import jakarta.ws.rs.core.NewCookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.info("User already exists!");
        }else {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .user_password(this.passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            this.userRepository.save(user);
        }
        return "Successfully registered";
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user =this.userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken =this.jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
