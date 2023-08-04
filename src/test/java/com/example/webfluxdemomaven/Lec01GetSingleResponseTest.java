package com.example.webfluxdemomaven;

import com.example.webfluxdemomaven.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseTest extends BaseTest{
    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
       Response response= this.webClient.get()
                .uri("reactive-math/square/{number}",5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();
        System.out.println(
                response
        );
    }

    @Test
    public void stepVerifierTest(){
        Mono<Response> monoResponse= this.webClient.get()
                .uri("reactive-math/square/{number}",5)
                .retrieve()
                .bodyToMono(Response.class);

        StepVerifier.create(monoResponse)
                .expectNextMatches( r -> r.getOutput() == 25)
                .verifyComplete();
    }
}
