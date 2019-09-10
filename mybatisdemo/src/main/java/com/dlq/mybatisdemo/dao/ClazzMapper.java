package com.dlq.mybatisdemo.dao;

import com.dlq.mybatisdemo.model.Class;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzMapper {
    List getclass(String clazzname);
}
