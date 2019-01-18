package com.onegene.server.selenium.controller;

import com.onegene.server.selenium.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName PdfController
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/15 9:54
 **/

@Controller
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @RequestMapping(value = "/generate/pdf")
    public String generatePdf(HttpServletRequest request) throws InterruptedException, AWTException, IOException {

        request.getSession().removeAttribute("totalCount");
        request.getSession().removeAttribute("curCount");
        request.getSession().removeAttribute("percent");
        request.getSession().removeAttribute("percentText");



        pdfService.pdfExport(request);
        return "/";
    }


    @ResponseBody
    @RequestMapping(value = "/pdf/progress", method = RequestMethod.GET)
    public Object download(HttpServletRequest request) {


        HashMap<String, Object> map = null;
        try {
            HttpSession session = request.getSession();
            map = new HashMap<>();
            map.put("totalCount", session.getAttribute("totalCount"));//总条数
            map.put("curCount", session.getAttribute("curCount"));// 已导条数
            map.put("percent", session.getAttribute("percent"));//百分比数字
            map.put("percentText", session.getAttribute("percentText"));//百分比文本
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }
}
