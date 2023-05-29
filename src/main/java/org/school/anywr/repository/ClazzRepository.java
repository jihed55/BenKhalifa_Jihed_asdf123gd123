package org.school.anywr.repository;

import org.school.anywr.model.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {
  Page<Clazz> findByName(String name, PageRequest pageRequest);
}
