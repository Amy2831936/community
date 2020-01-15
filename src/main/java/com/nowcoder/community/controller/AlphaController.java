package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("hello")
    @ResponseBody
    public String sayHello() {
        return "hello spring boot";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request , HttpServletResponse response) {
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames(); //得到请求行的key
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset = utf-8");
        try {
            PrintWriter writer = response.getWriter();

            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求

    //  /students?current=1&limit=20
    @RequestMapping(path = "/students" , method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current" , required = false , defaultValue = "1") int current , //表示可以不传，不传时默认值为1
            @RequestParam(name = "current" , required = false , defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //  /student/123
    @RequestMapping(path = "/student/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path = "/student" , method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name , int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应动态html数据
    @RequestMapping(path = "/teacher" , method = RequestMethod.GET)
    public ModelAndView getTeacher() {  //这里返回的是网页，所以不需要@ResponseBody
        ModelAndView mav = new ModelAndView();
        mav.addObject("name" , "张三");
        mav.addObject("age" , 30);
        mav.setViewName("/demo/view"); //这个模板在template , 这里的view是view.html
        return mav;
    }

    //响应JSON数据（异步请求）
    //Java对象 -》 JSON字符串 -》 JS对象
    @RequestMapping(path = "/emp" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String , Object> getEmp() {
        Map<String , Object> emp = new HashMap<>();
        emp.put("name" , "张三");
        emp.put("age" , 23);
        emp.put("salary" , 8000.00);
        return emp;
    }
}
