package org.school.anywr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.school.anywr.dto.out.StudentDTO;
import org.school.anywr.model.Student;
import org.school.anywr.security.JwUtils;
import org.school.anywr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/student")
@Tag(name = "Student Controller", description = "Student Entity APIs")
public class StudentController {

  public static final String DEFAULT_VALUE_PAGE = "0";
  public static final String DEFAULT_PAGE_SIZE = "100";

  @Autowired private StudentService studentService;
  
  @Autowired
  JwUtils jwUtils;

  @GetMapping("/all")
  @Operation(
      summary = "Filter Student",
      description = "return student by teacher full name or class name")
  public ResponseEntity<Page<StudentDTO>> getAllStudents(
      @RequestParam(name = "className", required = false) String className,
      @RequestParam(name = "teacherFullName", required = false) String teacherFullName,
      @RequestParam(name = "page", defaultValue = DEFAULT_VALUE_PAGE) int page,
      @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,Authentication auth) {
	  return  ResponseEntity.ok().body( studentService.getAllStudents(className, teacherFullName, page, size));
  }
  

  @PostMapping("/save")
  public void saveStudents(@RequestBody Student key) {
    studentService.save(key);
  }
}
