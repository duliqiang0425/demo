package com.dlq.mybatisdemo.dao;

import com.dlq.mybatisdemo.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    List<Teacher> getTeacher(String project);

    List<Teacher> getTeacherStudnetClazz(String project);
}
