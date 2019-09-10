package com.dlq.mybatisdemo.service;

import com.dlq.mybatisdemo.model.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getTeacherByProject(String project);
    public List<Teacher> getTeacherAll(String project);
}
