package com.dlq.mybatisdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class Teacher {
    private Integer tid;

    private String teaname;

    private String project;

    private Integer teaage;

    private String clazzname;

    private List<Student> studentList;

    private Class clazz;
}
