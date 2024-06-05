package com.encurtator.link.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private Integer code;
    private String message;
}
