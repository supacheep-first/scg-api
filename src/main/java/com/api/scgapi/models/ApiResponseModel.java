package com.api.scgapi.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponseModel {
    private HttpStatus status;
    private String body;
}
