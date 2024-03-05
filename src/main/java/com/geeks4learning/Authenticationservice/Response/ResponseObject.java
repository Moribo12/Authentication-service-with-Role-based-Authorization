package com.geeks4learning.Authenticationservice.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject<T> {

    private Integer statusCode;
    private String message;
    private T data;
}
