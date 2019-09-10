package com.dlq.mybatisdemo.model;

import lombok.Data;

import java.util.List;

@Data
public class Class {
    private Integer cid;

    private String clazzname;

    private String peopleNum;

    private List<Class> clazzlist;

}
