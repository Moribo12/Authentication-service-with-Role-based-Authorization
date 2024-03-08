package com.geeks4learning.Authenticationservice.Service;

import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RefreshTokenRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RegisterRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;
import com.geeks4learning.Authenticationservice.Dto.Response.RegistrationResponse;
import com.geeks4learning.Authenticationservice.Model.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    public String register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

   public void validateToken(String token);

   public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}