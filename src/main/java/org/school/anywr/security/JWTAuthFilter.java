package org.school.anywr.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager manager;
  private JwUtils jwUtils;

  public JWTAuthFilter(AuthenticationManager manager, JwUtils jwUtils) {
    super();
    this.manager = manager;
    this.jwUtils = jwUtils;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(username, password);
    return manager.authenticate(usernamePasswordAuthenticationToken);
  }

  @Override
  public void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {

    String jwt = jwUtils.generateJwtToken(authResult);
    response.setHeader("Authorization", jwt);
    super.successfulAuthentication(request, response, chain, authResult);
  }
}
