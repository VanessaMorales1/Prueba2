package com.distribuida;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import javax.ws.rs.core.Application;


@OpenAPIDefinition(info = @Info(
        title = "app-authors",
        version = "1.0.0",
        contact = @Contact(
                name = "vanessa",
                email = "vlmorales@uce.edu.ec"
        )

))
public class RestApp extends Application {
}
