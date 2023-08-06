package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    //> min && < max
   // Flux<Product> findByPriceBetween(int min,int max);
    Flux<Product> findByPriceBetween(Range<Integer> range);
}
