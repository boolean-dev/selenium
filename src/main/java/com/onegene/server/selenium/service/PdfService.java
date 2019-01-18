package com.onegene.server.selenium.service;

import com.onegene.server.selenium.entity.Sample;
import com.onegene.server.selenium.utils.SeleniumUtils;
import com.pdfcrowd.Pdfcrowd;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Key;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @ClassName PdfService
 * @Descriiption PdfService
 * @Author yanjiantao
 * @Date 2019/1/15 9:55
 **/
@Service
@Slf4j
public class PdfService {

    @Autowired
    private SampleService sampleService;

    public static final String URL = "http://report.1genehealth.com/static/report_adult_2018bx-V1_print/index.html?sampleCode=189874042103&tdsourcetag=s_pctim_aiomsg";

    private WebDriver webDriver = null;

    private String currentWin = null;

    public void pdfExport(HttpServletRequest request) throws InterruptedException, IOException, AWTException {


        webDriver = SeleniumUtils.openAccess();

        List<Sample> samples = sampleService.findAll();
        HashMap<String, Object> map = null;
        HttpSession session = null;
        try {
            session = request.getSession();
            session.setAttribute("totalCount", samples.size());
            session.setAttribute("curCount", 0);
            session.setAttribute("percent", 0.00d);
            session.setAttribute("percentText", "0%");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < samples.size(); i++) {
            this.getHtml(samples.get(i));
            session = request.getSession();
            int totalCount = (int) session.getAttribute("totalCount");
            session.setAttribute("curCount", i+1);
            double percent = new BigDecimal((i+1.0)/totalCount).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            session.setAttribute("percent", percent);
            session.setAttribute("percentText",String.valueOf(percent*100) + "%");
            log.info("-----------------------i={}",i++);
        }

        webDriver.close();

    }

    private void getHtml(Sample sample) throws InterruptedException, AWTException, IOException {
        webDriver.get(sample.getUrl());

        Thread.sleep(500);
        WebElement addpBtn = webDriver.findElement(By.className("addp"));
        addpBtn.click();

        WebElement genLayoutBtn = webDriver.findElement(By.id("genLayout"));
        genLayoutBtn.click();

        if (currentWin == null) {
            currentWin = webDriver.getWindowHandle();
        }


        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (currentWin.equals(handle)) continue;
            webDriver = webDriver.switchTo().window(handle);
        }

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(4000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(100);


        Runtime runtime = Runtime.getRuntime();
        String exeDir = System.getProperty("user.dir")+ "\\src\\main\\resources\\driver\\" + "pdf.exe";
        log.info(sample.getCode());
        String[] commandArray = {exeDir, sample.getCode() + ".pdf"};
        runtime.exec(commandArray);

        Thread.sleep(2000);

        webDriver.close();
        webDriver = webDriver.switchTo().window(currentWin);


    }


}
