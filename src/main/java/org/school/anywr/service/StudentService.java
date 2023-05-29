package org.school.anywr.service;

import java.util.function.Function;
import org.modelmapper.ModelMapper;
import org.school.anywr.dto.out.StudentDTO;
import org.school.anywr.model.Student;
import org.school.anywr.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired private StudentRepository studentRepository;

  @Autowired private ModelMapper mapper;

  public Page<StudentDTO> getAllStudents(
      String className, String teacherFullName, int page, int size) {
    Pageable pageReq = PageRequest.of(page, size);
    String[] teacherName = extractTeacherName(teacherFullName);
    if (className != null && teacherFullName != null) {
      return studentRepository
          .findByClazz_NameAndClazz_Teacher_lastNameAndClazz_Teacher_firstName(
              className, teacherName[0], teacherName[1], pageReq)
          .map(function);
    }

    if (teacherFullName == null) {
      if (className == null) {
        return getAllStudents(page, size).map(function);
      }
      return getAllStudents(className, page, size).map(function);
    }

    return studentRepository
        .findByClazz_Teacher_lastNameAndClazz_Teacher_firstName(
            teacherName[0], teacherName[1], pageReq)
        .map(function);
  }

  Page<Student> getAllStudents(int page, int size) {
    PageRequest pageReq = PageRequest.of(page, size);
    return studentRepository.findAll(pageReq);
  }

  public Page<Student> getAllStudents(String className, int page, int size) {
    Pageable pageReq = PageRequest.of(page, size);
    return studentRepository.findByClazzName(className, pageReq);
  }

  public String[] extractTeacherName(String teacherFullName) {
    if (teacherFullName != null) {
      return teacherFullName.split(" ");
    }
    return new String[2];
  }

  Function<Student, StudentDTO> function = (Student t) -> mapper.map(t, StudentDTO.class);

  public void save(Student key) {
    Student student = new Student();
    student.setFirstName(key.getFirstName());
    student.setLastName(key.getLastName());
    if (key.getClass() != null) {
      // Clazz clazz = classRepository.findByName(key.getClazz().getName());
      studentRepository.save(student);
    }
  }
}
