package com.dlq.mybatisdemo.service.impl;

import com.dlq.mybatisdemo.dao.ClazzMapper;
import com.dlq.mybatisdemo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List getclazz(String clazzname) {
        List clazz =  clazzMapper.getclass(clazzname);
        return clazz;
    }
}
