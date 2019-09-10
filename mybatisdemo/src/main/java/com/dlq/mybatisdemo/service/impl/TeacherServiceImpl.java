package com.dlq.mybatisdemo.service.impl;

import com.dlq.mybatisdemo.dao.TeacherMapper;
import com.dlq.mybatisdemo.model.Teacher;
import com.dlq.mybatisdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeacherByProject(String project) {
        List<Teacher> teacherList = teacherMapper.getTeacher(project);
        return teacherList;
    }

    public List<Teacher> getTeacherAll(String project){
        List<Teacher> teacherList = teacherMapper.getTeacherStudnetClazz(project);
        return teacherList;
    }

}
