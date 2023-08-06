package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public class DataSetupService implements CommandLineRunner {
   // @Autowired
    private ProductService service;
    @Override
    public void run(String... args) throws Exception {

        ProductDto p1=new ProductDto("guitar",1000);
        ProductDto p2=new ProductDto("violin",1500);
        ProductDto p3=new ProductDto("key-chain",200);
        ProductDto p4=new ProductDto("air-pod",1000);

        Flux.just(p1,p2,p3,p4)
                .flatMap(p -> this.service.insertProduct(Mono.just(p)))
                .subscribe(System.out::println);


    }
}
