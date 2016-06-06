package com.spring.chapter5.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 声明为一个控制器
@RequestMapping("/")
public class HomeController {

  @RequestMapping(method = GET) // 处理对 "/" 的请求.
  public String home() {
    return "home"; // 视图名为home.
  }
}
