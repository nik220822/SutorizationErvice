package com.nicode.autorizationservice.controller;

import com.nicode.autorizationservice.authorities.Authorities;
import com.nicode.autorizationservice.exception.InvalidCredentials;
import com.nicode.autorizationservice.exception.UnauthorizedUser;
import com.nicode.autorizationservice.service.AuthorizationService;
import com.nicode.autorizationservice.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {
	private final AuthorizationService service;

	public AuthorizationController(AuthorizationService service) {
		this.service = service;
	}
	
	@GetMapping("/authorize")
	public List<Authorities> getAuthorities(@Valid User user) {
		return service.getAuthorities(user);
	}

	@ExceptionHandler(InvalidCredentials.class)
	ResponseEntity<String> handler1(InvalidCredentials e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedUser.class)
	ResponseEntity<String> handler2(UnauthorizedUser e) {
		String msg = e.getMessage();
		System.out.println(msg);
		return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
	}
}
