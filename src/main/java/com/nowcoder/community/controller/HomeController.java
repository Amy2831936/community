package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index" , method = RequestMethod.GET)
    public String getIndexPage(Model model , Page page) {
        //方法调用之前，SpringMVC会自动实例化model和page，并将Page注入Model，
        //所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDisscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffSet(),page.getLimit());
        List<Map<String , Object>> discussPosts = new ArrayList<>();
        if(list != null) {
            for(DiscussPost post : list) {
                Map<String , Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user" , user);
                discussPosts.add(map);
            }
        }
        //把要给页面展示的结果放到model中,页面才能得到,后面这一项discussPosts是给数据起的名字
        model.addAttribute("discussPosts" , discussPosts);
        return "/index";
    }
}
