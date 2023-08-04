package com.example.webfluxdemomaven.controller;

import com.example.webfluxdemomaven.dto.MultiplyRequestDto;
import com.example.webfluxdemomaven.dto.Response;
import com.example.webfluxdemomaven.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {
    @Autowired
    private ReactiveMathService mathService;

    @GetMapping(value = "/square/{input}")
    public Mono<Response> findSquare(@PathVariable int input){
        return this.mathService.findSquare(input);
    }
    @GetMapping(value = "/table/{input}/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){

        return this.mathService.multiplicationTable(input);
    }
    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return this.mathService.multiplicationTable(input);
    }
    @PostMapping("/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> requestDto,
                                   @RequestHeader Map<String,String> headers){
        System.out.println(headers);
        return mathService.multiply(requestDto);
    }
}
