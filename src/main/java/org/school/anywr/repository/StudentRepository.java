package org.school.anywr.repository;

import org.school.anywr.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Page<Student> findByClazz_NameAndClazz_Teacher_lastNameAndClazz_Teacher_firstName(
      String className, String lastName, String firstName, Pageable pageRequest);

  Page<Student> findByClazzName(String className, Pageable pageReq);

  Page<Student> findByClazz_Teacher_lastNameAndClazz_Teacher_firstName(
      String string, String string2, Pageable pageReq);
}
