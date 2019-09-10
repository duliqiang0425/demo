package com.dlq.mybatisdemo.service.impl;

import com.dlq.mybatisdemo.dao.StudentMapper;
import com.dlq.mybatisdemo.model.Student;
import com.dlq.mybatisdemo.service.StudentService;
import com.dlq.mybatisdemo.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageInfo<Student> selectAll(int pageNo, int pageSize,String numberCode) {

        String key = "student_list";
        //Boolean hasKey = redisTemplate.hasKey(key);
        Boolean hasKey = redisUtil.exists(key);

        //ValueOperations operations = redisTemplate.opsForValue();

        /*if (hasKey) {
            String redisList = (String) operations.get(key);

            Type type = new TypeToken<List<Student>>() {}.getType();
            List<Student> list =  new Gson().fromJson(redisList,type);

            System.out.println("StudentServiceImpl.selectAll() : 从缓存取得数据，条数：" + list.size());
            return page;
        }*/
        /*if(!hasKey){
                PageHelper.startPage(pageNo,pageSize);
                String redisList = redisUtil.get(key,0);
                Type type = new TypeToken<List<Student>>(){}.getType();
                List<Student> list = new Gson().fromJson(redisList,type);
                PageInfo<Student> page = new PageInfo<Student>(list);

                log.info("从redis中取得数据");
                return page;
        }*/
        PageHelper.startPage(pageNo,pageSize);
        List<Student> list = studentMapper.selectAll(numberCode);
        String toJson = new Gson().toJson(list);
        PageInfo<Student> page = new PageInfo<Student>(list);
        // 存在到缓存中
        //operations.set(key, toJson, 60, TimeUnit.SECONDS);
        redisUtil.set(key,toJson,0);
        return page;
    }

    public List<Student> getStudents(String clazzname){
        List<Student> studentList = studentMapper.selectByClazz(clazzname);
        return studentList;
    }

    /**
     * in的使用
     * @param clazzList
     * @return
     */
    public List<Student> getStudentsByClazz(List clazzList){
        List<Student> studentList = studentMapper.selectByClazzList(clazzList);
        return studentList;
    }

    /**
     * 时间戳的使用
     * @param starttime
     * @return
     */
    public List<Student> getStudentsByTime(String starttime){
        List<Student> studentList = studentMapper.selectByStartTime(starttime);
        return studentList;
    }
    public int updateAge(Student student){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = sdf.format(new Date().getTime());
        System.out.println(data);
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Calendar.getInstance().getTime());


        int updateAge = studentMapper.updateAge(student);
        return updateAge;
    }

    /**
     * 获取 一位 学生逻辑：
     * 如果缓存存在，从缓存中获取学生信息
     * 如果缓存不存在，从 DB 中获取学生信息，然后插入缓存
     */
    @Override
    public Student getStudent(String numberCode) {
        // 从缓存中 取出学生信息
        String key = "student_" + numberCode;
        Boolean hasKey = redisTemplate.hasKey(key);

        ValueOperations operations = redisTemplate.opsForValue();
        // 缓存存在
        if (hasKey) {
            String str = (String) operations.get(key);
            Student student = new Gson().fromJson(str, Student.class);
            System.out.println("StudentServiceImpl.getStudent() : 从缓存取得数据 >> " + student.toString());
            return student;
        }
        Student student = studentMapper.getStudent(numberCode);
        String str = new Gson().toJson(student);
        // 插入缓存中
        operations.set(key, str, 10, TimeUnit.SECONDS);
        System.out.println("StudentServiceImpl.getStudent() : 学生信息插入缓存 >> " + student.toString());
        return student;
    }

    @Override
    public int delete(String numberCode) {

        String key = "student_" + numberCode;
        Boolean hasKey = redisTemplate.hasKey(key);

        int delete = studentMapper.delete(numberCode);
        if( delete > 0){
            // 缓存存在，进行删除
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("StudentServiceImpl.update() : 从缓存中删除编号学生 >> " + numberCode);
            }
        }
        return delete;
    }

    /**
     * 更新学生信息逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public int update(Student student) {

        String key = "student_" + student.getNumbercode();
        Boolean hasKey = redisTemplate.hasKey(key);

        int update = studentMapper.update(student);

        if( update > 0 ){
            // 缓存存在，进行删除
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("StudentServiceImpl.update() : 从缓存中删除学生 >> " + student.toString());
            }

        }
        return update;
    }

    @Override
    public int insert(Student student) {

        String key = "student_list";

        int insert = studentMapper.insert(student);
        if (insert > 0) {
            redisTemplate.delete(key);
        }
        return insert;
    }

    /**
     * 批量插入学生
     * @param student
     * @return
     */
    public int savaStudent(List<Student> student){
        int studentNum = studentMapper.insertStudentList(student);
        return studentNum;
    }
}
