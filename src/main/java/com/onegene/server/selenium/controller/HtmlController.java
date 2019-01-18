package com.onegene.server.selenium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName HtmlController
 * @Descriiption HtmlController
 * @Author yanjiantao
 * @Date 2019/1/14 14:45
 **/
@Controller
public class HtmlController {

    @RequestMapping(value = "/html/report_adult_2018bx-V1_print/*", method = RequestMethod.GET)
    public void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
       /* byte[] data = IOUtils.toByteArray(new FileInputStream("D:\\html\\test1.html"));
        OutputStream outputStream = response.getOutputStream();
        IOUtils.write(data,outputStream);*/
    }
}
