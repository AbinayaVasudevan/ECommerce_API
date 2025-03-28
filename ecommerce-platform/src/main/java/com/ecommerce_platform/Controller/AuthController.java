package com.ecommerce_platform.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_platform.DTO.LoginRequest;
import com.ecommerce_platform.DTO.Response;
import com.ecommerce_platform.DTO.UserDto;
import com.ecommerce_platform.Service.AuthService;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }
    
	@PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserDto registrationRequest){
		System.out.println("Received Registration Request: " + registrationRequest);
	    
	    Response response = service.registerUser(registrationRequest); // Call service method
	    
	    System.out.println("Response: " + response); // Log the response before returning it
	    
	    return ResponseEntity.ok(response); // Return the response
    }
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(service.loginUser(loginRequest));
    }
}
