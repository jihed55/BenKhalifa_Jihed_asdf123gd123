package org.school.anywr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "STUDENT")
public class Student {
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = Constants.LAST_NAME)
  private String lastName;

  @Column(name = Constants.FIRST_NAME)
  private String firstName;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private Clazz clazz;
}
