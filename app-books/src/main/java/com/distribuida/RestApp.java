package com.distribuida;
import jakarta.ws.rs.ApplicationPath;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;


@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "app-books",
        version = "1.0.0",
        contact = @Contact(
                name = "vanessa",
                email = "vlmorales@uce.edu.ec"
        )

))
public class RestApp extends Application {

}
