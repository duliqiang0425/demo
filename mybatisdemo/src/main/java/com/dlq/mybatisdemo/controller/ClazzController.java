package com.dlq.mybatisdemo.controller;

import com.dlq.mybatisdemo.model.Class;
import com.dlq.mybatisdemo.service.ClazzService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/getClazzandstudent")
    public List<Class> getClazzandstudent(@RequestParam(value="clazzname") String clazzname){
        List clazz = clazzService.getclazz(clazzname);
        //String str = new Gson().toJson(clazz);
        return clazz;
    }
}
