package men.okuuim.contollers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import men.okuuim.service.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentReuter {

    @Bean
    public RouterFunction<ServerResponse> routes(StudentHandler studentHandler) {
        return RouterFunctions.route()
                .path("/student", builder -> builder
                        .GET("", studentHandler::getAll)
                        .GET("/{id}", studentHandler::getByIdQuery)
                        .POST("", studentHandler::save)
                        .DELETE("/{id}", studentHandler::delete))
                .build();
    }

}
