package com.dlq.mybatisdemo.dao;

import com.dlq.mybatisdemo.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    //@Select("select * from student")
    List<Student> selectAll(String numberCode);

    List<Student> selectByClazz(String clazzname);

    List<Student> selectByClazzList(List clazzList);

    List<Student> selectByStartTime(String starttime);
    int updateAge(Student student);

    int count();

    //@Select("select * from student where Number_Code = #{numberCode}")
    Student getStudent(@Param("numberCode") String numberCode);

    //@Delete("delete from student where Number_Code = #{numberCode}")
    int delete(@Param("numberCode") String numberCode);

    int update(Student student);

    int insert(Student student);

    int insertStudentList(@Param(value = "studentList") List<Student> student);
}