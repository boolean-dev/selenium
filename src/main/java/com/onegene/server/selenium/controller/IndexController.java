package com.onegene.server.selenium.controller;

import com.onegene.server.selenium.entity.Pageable;
import com.onegene.server.selenium.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @Autowired
    private SampleService sampleService;



    /*@RequestMapping(value = "/*", method = RequestMethod.GET)
    public String index(int page) {
        return "index";
    }*/

    /**
     * 主页
     * @param model model and view
     * @param pageable  page info
     * @return
     */
    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String list(ModelMap model, Pageable pageable){

        model.addAttribute("page", sampleService.findPage(pageable));
        return "index";
    }

    /**
     * 清除样本
     * @return  重定向
     */
    @RequestMapping(value = "/sample/clean", method = RequestMethod.GET)
    public String clean() {
        sampleService.clean();
        return "redirect:/";
    }


}
