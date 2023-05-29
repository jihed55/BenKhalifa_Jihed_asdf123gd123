package org.school.anywr.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SignUpRequestBodyDTO {
  @EqualsAndHashCode.Include
  @JsonProperty("username")
  @NotBlank
  public String userName;

  @EqualsAndHashCode.Include @NotBlank public String password;
}
