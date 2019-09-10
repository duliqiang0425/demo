package com.dlq.mybatisdemo.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MbException extends RuntimeException {
    private Integer code;
    private String msg;

    public MbException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
