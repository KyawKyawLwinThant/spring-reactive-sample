package com.example.webfluxdemomaven.config;

import com.example.webfluxdemomaven.dto.InputFailedValidationResponse;
import com.example.webfluxdemomaven.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
public class RouterConfig {
    @Autowired
    private RequestHandler requestHandler;

  //  @Bean
    public RouterFunction<ServerResponse> highLevelRouter (){
        return RouterFunctions.route()
                .path("router",this::serverResponseRouterFunction)
                .build();
    }


    private RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("square/{input}", RequestPredicates.path("*/1?")
                        .or(RequestPredicates.path("*/20")),requestHandler::squareHandler)
                .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("only 10 -19 allowed"))
                .GET("table/{input}",requestHandler::tableHandler)
                .GET("table/{input}/stream",requestHandler::tableStreamHandler)
                .POST("multiply",requestHandler::multiplyHandler)
                .GET("square/{input}/validation",requestHandler::squareHandlerWithValidation)
                .onError(InputValidationException.class,exceptionHandler())
                .build();
    }
    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (error, req) -> {
            InputValidationException ex= (InputValidationException)error;
            InputFailedValidationResponse response=new InputFailedValidationResponse();

            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
