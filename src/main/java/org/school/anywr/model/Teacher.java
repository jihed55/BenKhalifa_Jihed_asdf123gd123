package org.school.anywr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.school.anywr.utils.Constants;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "TEACHER")
public class Teacher {
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = Constants.LAST_NAME)
  private String lastName;

  @Column(name = Constants.FIRST_NAME)
  private String firstName;
}
