package org.school.anywr.controller;

import java.util.List;

import javax.validation.Valid;

import org.school.anywr.dto.in.SignINRequestBodyDTO;
import org.school.anywr.dto.in.SignUpRequestBodyDTO;
import org.school.anywr.model.UserApp;
import org.school.anywr.security.JwUtils;
import org.school.anywr.service.UserDetailServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthentificationController {
  @Autowired UserDetailServiceIpml userDetailServiceIpml;
  @Autowired private JwUtils jwUtils;
  @Autowired private AuthenticationManager auth;

  @RequestMapping("/register")
  public void register(@Valid @RequestBody SignUpRequestBodyDTO user) {
    userDetailServiceIpml.saveUser(user);
  }

  @GetMapping("/all")
  public List<UserApp> getAllUser() {
    return userDetailServiceIpml.getAllUser();
  }

  @RequestMapping("/login")
  @Operation(
	      summary = "User Authentication",
	      description = "Authentify the user and return JWT")
  public ResponseEntity<Object> login(@RequestBody SignINRequestBodyDTO user) throws Exception {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
    try {
      Authentication res = auth.authenticate(usernamePasswordAuthenticationToken);
      final UserApp userDetails =
          (UserApp) userDetailServiceIpml.loadUserByUsername(user.getUserName());

      final String token = jwUtils.generateJwtToken(res);
      return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(userDetails);
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
