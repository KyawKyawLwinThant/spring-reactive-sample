package com.example.webfluxdemomaven.config;

import com.example.webfluxdemomaven.dto.InputFailedValidationResponse;
import com.example.webfluxdemomaven.dto.MultiplyRequestDto;
import com.example.webfluxdemomaven.dto.Response;
import com.example.webfluxdemomaven.exception.InputValidationException;
import com.example.webfluxdemomaven.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {
    @Autowired private ReactiveMathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input=Integer.valueOf(serverRequest.pathVariable("input"));
        Mono< Response> responseMono =this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono,Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux=this.mathService.multiplicationTable(input);
        return ServerResponse.ok().body(responseFlux,Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux=this.mathService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux,Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
         Mono<MultiplyRequestDto> requestDtoMono=serverRequest.bodyToMono(MultiplyRequestDto.class);
         Mono<Response> responseMono=this.mathService.multiply(requestDtoMono);
         return ServerResponse.ok()
                 .body(responseMono,Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int input=Integer.parseInt(serverRequest.pathVariable("input"));
        if(input < 10 || input > 20){
           /* InputFailedValidationResponse response=
                    new InputFailedValidationResponse();
            return ServerResponse.badRequest().bodyValue(response);*/
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> responseMono=this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono,Response.class);
    }
}