package com.example.userservice.service;


import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto>  all(){
        return this.userRepository.findAll()
                .map(EntityDtoUtil::userDto);
    }

    public Mono<UserDto> getUserById(final int userId){
        return this.userRepository.findById(userId)
                .map(EntityDtoUtil::userDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::userDto);

    }

    public Mono<UserDto> updateUser(int id,Mono<UserDto> userDtoMono){
        return this.userRepository
                .findById(id)
                .flatMap(u -> userDtoMono.map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.userRepository::save)
                .map(EntityDtoUtil::userDto);
    }

    public Mono<Void> deleteUser(int id){
        return this.userRepository.deleteById(id);
    }
}
