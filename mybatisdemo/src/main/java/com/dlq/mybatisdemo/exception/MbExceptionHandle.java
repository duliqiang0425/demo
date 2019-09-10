package com.dlq.mybatisdemo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MbExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Object Handle(Exception e){

        if(e instanceof MbException){
            MbException mbException = (MbException) e;
            String[] message = {Integer.toString(mbException.getCode()),mbException.getMsg()};
            return message;
        }else {

            return "全局错误";
        }
    }
}
