package com.dlq.mybatisdemo.controller;

import com.dlq.mybatisdemo.model.Teacher;
import com.dlq.mybatisdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(path = "/getTeacher")
    public List<Teacher> getTeacherByProject(@RequestParam(value = "project") String project){
      List<Teacher> teacherList = teacherService.getTeacherByProject(project);
        //String teacherString = new Gson().toJson(teacher);
        return teacherList;
    }

    @GetMapping(path = "/getTeacherAll")
    public List<Teacher> getTeacherAll(@RequestParam(value = "project")String project){
        List<Teacher> teacherList = teacherService.getTeacherAll(project);
        return teacherList;
    }
}
