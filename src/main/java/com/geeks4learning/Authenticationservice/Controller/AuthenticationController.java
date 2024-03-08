package com.geeks4learning.Authenticationservice.Controller;

import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RefreshTokenRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RegisterRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;
import com.geeks4learning.Authenticationservice.Dto.Response.RegistrationResponse;
import com.geeks4learning.Authenticationservice.Model.User;
import com.geeks4learning.Authenticationservice.Response.ResponseObject;
import com.geeks4learning.Authenticationservice.Service.AuthenticationServiceImp;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImp authenticationServiceImp;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        try {
            this.authenticationServiceImp.register(request);
            return new ResponseEntity<>("Successfully Registered",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Registration Failed !",HttpStatus.BAD_REQUEST);
}

    @PostMapping("/authenticate")
    public ResponseObject<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
    try {
         return new ResponseObject<>(200, "Authenticated Successfully", this.authenticationServiceImp.authenticate(request));
      }catch (Exception e){
        e.printStackTrace();
    }
    return new ResponseObject<>(401,"Authentication failed!",null);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token) {
        try {
            this.authenticationServiceImp.validateToken(token);
            return new ResponseEntity<>("Token is valid",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Token is Invalid",HttpStatus.BAD_REQUEST);
     }

    @PostMapping("/refresh")
    public ResponseObject<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            return new ResponseObject<>(200, "Refreshed Successfully", this.authenticationServiceImp.refreshToken(refreshTokenRequest));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseObject<>(401,"Failed to refresh",null);
    }



}
