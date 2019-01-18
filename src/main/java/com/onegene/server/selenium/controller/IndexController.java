package com.onegene.server.selenium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName IndexController
 * @Descriiption IndexController
 * @Author yanjiantao
 * @Date 2019/1/17 17:06
 **/
@Controller
public class IndexController {



    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
