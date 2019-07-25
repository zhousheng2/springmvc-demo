package com.example.springmvc.handler;

import com.example.springmvc.annotation.Controller;
import com.example.springmvc.annotation.RequestMapping;
import com.example.springmvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Despriction: 一个Controoler类可以编写N个方法
 * @Author: zhousheng
 * @CreatedTime: 2019-07-25 15:23
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
@Controller
public class UserController {

    @RequestMapping(value = "/query")
    @ResponseBody
    public Map<String, Object> query(Integer id, String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        return map;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id) {
        return "OK";
    }
}
