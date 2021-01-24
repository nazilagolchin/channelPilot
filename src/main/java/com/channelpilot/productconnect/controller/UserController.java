package com.channelpilot.productconnect.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.channelpilot.productconnect.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	/**
	 * get JWTTOKEN and prepare user
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 */
	@PostMapping("api/user")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		String token = getJWTToken(username);
		User user = new User();
		user.setUser(username);
		user.setToken(token);
		return user;
	}

	/**
	 * generate new Bearer TOKEN by id and sign JWT key by secretKey and set key
	 * expiration after 600000 milliseconds after Issued time
	 * 
	 * @param username
	 * @return
	 */
	private String getJWTToken(String username) {
		String secretKey = "12345-5433-3543-54321";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("channelPilotJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

}
