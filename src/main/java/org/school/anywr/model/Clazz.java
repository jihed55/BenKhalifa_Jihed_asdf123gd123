package org.school.anywr.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.school.anywr.utils.Constants;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CLASS")
public class Clazz {

  @Id
  @Column(name = Constants.ID)
  private Long id;

  @Column(name = Constants.NAME)
  private String name;

  @OneToMany(mappedBy = "clazz")
  private List<Student> students;

  @OneToOne
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;
}
