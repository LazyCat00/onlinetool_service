package com.nnutc.system.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolException extends RuntimeException{
    private Integer code;
    private String msg;

}