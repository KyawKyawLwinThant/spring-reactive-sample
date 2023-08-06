package com.example.userservice.dto;

import lombok.Data;
import org.springframework.web.util.pattern.PathPattern;

@Data
public class TransactionResponseDto {

    private Integer userId;
    private Integer amount;
    private TransactionStatus status;
}
