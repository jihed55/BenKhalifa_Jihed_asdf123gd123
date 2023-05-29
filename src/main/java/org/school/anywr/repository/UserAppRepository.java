package org.school.anywr.repository;

import java.util.Optional;
import org.school.anywr.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {

  Optional<UserApp> findByName(String username);
}
