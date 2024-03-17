package com.bkool.rest.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/*Bug con spring 3 no muestra el botón de autorizar en swagger para añadir el JWT, se ha tenido que añadir esta clase
de config*/
@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "jwtAuth", scheme = "bearer", bearerFormat = "JWT")
public class ApiConfig {
}
