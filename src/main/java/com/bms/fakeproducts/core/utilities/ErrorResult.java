package com.bms.fakeproducts.core.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String message;
    private LocalDateTime timestamp;
    private String httpStatus;
}
