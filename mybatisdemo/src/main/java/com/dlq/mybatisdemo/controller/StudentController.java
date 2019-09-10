package com.dlq.mybatisdemo.controller;

import com.dlq.mybatisdemo.model.Student;
import com.dlq.mybatisdemo.service.StudentService;
import com.dlq.mybatisdemo.utils.IPUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping( path = "/getList")
    public PageInfo<Student> getList(HttpServletRequest request, @RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="3")int pageSize,
                                     @RequestParam(value="numberCode") String numberCode){
        log.info(IPUtils.getIpAddr(request)+":获取了学生列表信息");
        PageInfo<Student> page = studentService.selectAll(pageNo,pageSize,numberCode);
        return page;
    }

    @GetMapping(path = "/getListByClazz")
    public List<Student> getListByClazz(@RequestParam(value = "clazzname")String clazzname){
        List<Student> studentList = studentService.getStudents(clazzname);
        return studentList;
    }

    @GetMapping( path = "/getStudent")
    public String getList(@Param("numberCode") String numberCode){
        Student students = studentService.getStudent(numberCode);
        String str = new Gson().toJson(students);
        return str;
    }

    /**
     * in 的用法
     * @param clazzList
     * @return
     */
    @GetMapping(path = "/getstudentbyclazz")
    public List<Student> getstudentbyclazz(@RequestParam List clazzList){
        List<Student> studentList = studentService.getStudentsByClazz(clazzList);
        return studentList;
    }

    /**
     * 时间戳的用法
     * @param starttime
     * @return
     */
    @GetMapping(path="/getstudentbytime")
    public List<Student> getStudentByTime(@RequestParam(value = "Starttime")String starttime){
        List<Student> studentList = studentService.getStudentsByTime(starttime);
        return studentList;
    }

    @PostMapping(value = "/updateage")
    public int updateAge(@RequestBody Student student){
        int updatesum = studentService.updateAge(student);
        return updatesum;
    }

    /**
     * 利用foreach批量插入学生
     * @param student
     * @return
     */
    @PostMapping(path = "saveStudent")
    public int saveStudent(@RequestBody List<Student> student){
        int studentNum = studentService.savaStudent(student);
        return studentNum;
    }

    @PostMapping(path = "/insert")
    public String insert(@RequestBody Student student){
        int insert = studentService.insert(student);
        String msg = "";
        if( insert > 0 ){
            msg = "{\"msg\":\"新增成功\",\"flag\":true}";
        }else {
            msg = "{\"msg\":\"新增失败\",\"flag\":false}";
        }
        return msg;
    }

    @GetMapping(path = "/delete")
    public String delete(@Param("numberCode") String numberCode){
        int delete = studentService.delete(numberCode);
        String msg = "";
        if( delete > 0 ){
            msg = "{\"msg\":\"删除成功！！\",\"flag\":true}";
        }else {
            msg = "{\"msg\":\"删除失败！！\",\"flag\":false}";
        }
        return msg;
    }

    @PostMapping(path = "/update")
    public String update(@RequestBody Student student){
        int update = studentService.update(student);
        String msg = "";
        if( update > 0 ){
            msg = "{\"msg\":\"更新成功！！！\",\"flag\":true}";
        }else {
            msg = "{\"msg\":\"更新失败！！！\",\"flag\":false}";
        }
        return msg;
    }

}
