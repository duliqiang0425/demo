package com.dlq.mybatisdemo.service;

import com.dlq.mybatisdemo.model.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    PageInfo<Student> selectAll(int pageNo, int pageSize,String numberCode);

    Student getStudent(String numberCode);

    List<Student> getStudents(String clazzname);

    List<Student> getStudentsByClazz(List clazzList);

    /**
     * 利用时间戳
     * @param starttime
     * @return
     */
    List<Student> getStudentsByTime(String starttime);
    int updateAge(Student student);


    int delete(String numberCode);

    int update(Student student);

    int insert(Student student);

    /**
     * savaStudent
     * @param student
     * @return
     */
    int savaStudent(List<Student> student);
}
