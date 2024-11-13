package com.vlad.menuservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class ApplicationConfig {
  @Bean
  public ModelMapper modelMapper() {

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    return modelMapper;
  }

//  @Bean
//  public OpenAPI userOpenAPI(
//          @Value("${openapi.service.title}") String serviceTitle,
//          @Value("${openapi.service.version}") String serviceVersion,
//          @Value("${openapi.service.url}") String url) {
//    return new OpenAPI()
//            .servers(List.of(new Server().url(url)))
//            .info(new Info().title(serviceTitle).version(serviceVersion));
//  }
}
