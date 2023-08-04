package com.example.webfluxdemomaven;

import com.example.webfluxdemomaven.dto.MultiplyRequestDto;
import com.example.webfluxdemomaven.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03HeadersTest extends BaseTest{
    @Autowired
    private WebClient webClient;

    @Test
    public void headersTest(){
        Mono<Response> responseMono=this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5,2))
                .headers( h -> h.set("someKey","someVal"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDto buildRequestDto(int a, int b){
        MultiplyRequestDto dto=new MultiplyRequestDto();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }
}
