package com.dlq.mybatisdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Student {
    private Integer sid;

    private String numbercode;

    private String stuname;

    private String stusex;

    private Integer stuage;

    private String clazzname;

    private String project;

    private String starttime;

    private String updatetime;
}