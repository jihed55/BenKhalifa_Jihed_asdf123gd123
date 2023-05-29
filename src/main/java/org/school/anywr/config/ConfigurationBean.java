package org.school.anywr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Value(value = "Student Management")
  private String title;

  @Bean
  public OpenAPI mdsOpenAPI() {
    return new OpenAPI().info(new Info().title(title).description(title + " API Documentation"));
  }

  @Bean
  OpenApiCustomiser removeServerCustomizer() {
    return openApi -> openApi.setServers(null);
  }
}
