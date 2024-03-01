package com.duvanlabrador.userApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private Date dateTime;
    private String developerMessage;
}
