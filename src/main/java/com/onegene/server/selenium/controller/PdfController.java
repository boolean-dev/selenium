package com.onegene.server.selenium.controller;

import com.onegene.server.selenium.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @ResponseBody
    @RequestMapping(value = "/generate/pdf" , method = RequestMethod.GET)
    public Object generatePdf(HttpServletRequest request) throws InterruptedException, AWTException, IOException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        pdfService.pdfExport(uuid);
        Map<String,String> result = new HashMap<>();
        result.put("uuid",uuid);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/pdf/progress", method = RequestMethod.GET)
    public Object progress(HttpServletRequest request,String uuid) {
        return pdfService.progress(uuid);
    }
}
