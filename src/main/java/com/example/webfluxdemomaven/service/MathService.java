package com.example.webfluxdemomaven.service;

import com.example.webfluxdemomaven.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {
    public Response findSquare(int input){
        return new Response(input * input);
    }
    public List<Response> multiplicationTable(int input){
        return IntStream.rangeClosed(1,10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service procession : "+ i))
                .mapToObj(i -> new Response( i * input))
                .collect(Collectors.toList());
       /* List<Response> list= IntStream.rangeClosed(1,10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> System.out.println("math-service procession : "+ i))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
        return Flux.fromIterable(list);*/
    }
}
