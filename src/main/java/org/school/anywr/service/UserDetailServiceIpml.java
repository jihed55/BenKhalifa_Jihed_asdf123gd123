package org.school.anywr.service;

import java.util.List;
import java.util.Optional;
import org.school.anywr.dto.in.SignUpRequestBodyDTO;
import org.school.anywr.model.UserApp;
import org.school.anywr.repository.UserAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceIpml implements UserDetailsService {
  private static final String NO_USER_MSG_STRING = "User Not Found with username: %s";

  @Autowired private UserAppRepository userAppRepository;

  @Autowired private PasswordEncoder encoder;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userAppRepository
        .findByName(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format(NO_USER_MSG_STRING, username)));
  }

  public void saveUser(SignUpRequestBodyDTO bodyDTO) {
    Optional<UserApp> b = userAppRepository.findByName(bodyDTO.getUserName());
    if (b.isPresent()) throw new IllegalArgumentException("User alrady exist");
    UserApp userApp = new UserApp();
    userApp.setPassword(encoder.encode(bodyDTO.getPassword()));
    userApp.setName(bodyDTO.getUserName());
    userAppRepository.save(userApp);
  }

  public List<UserApp> getAllUser() {
    return userAppRepository.findAll();
  }
}
