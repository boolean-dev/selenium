package com.onegene.server.selenium.service;

import com.onegene.server.selenium.entity.Progress;
import com.onegene.server.selenium.entity.Sample;
import com.onegene.server.selenium.utils.DriverPathUtils;
import com.onegene.server.selenium.utils.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.server.handler.interactions.touch.Up;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedissonClient redisson;

//    public static final String URL = "http://report.1genehealth.com/static/report_adult_2018bx-V1_print/index.html?sampleCode=189874042103&tdsourcetag=s_pctim_aiomsg";


    @Async
    public void pdfExport(String uuid){


        WebDriver webDriver = SeleniumUtils.openAccess();
        List<Sample> samples = sampleService.findAll();
        RAtomicLong totalCount = redisson.getAtomicLong("pdf-progress-totalCount:" + uuid);
        totalCount.expire(1, TimeUnit.DAYS);
        totalCount.set(samples.size());

        RAtomicLong curCount = redisson.getAtomicLong("pdf-progress-curCount:" + uuid);
        curCount.expire(1, TimeUnit.DAYS);
        curCount.set(0);

        RAtomicDouble percent = redisson.getAtomicDouble("pdf-progress-percent:" + uuid);
        percent.expire(1, TimeUnit.DAYS);
        percent.set(0.0);

        RBucket<String> percentText = redisson.getBucket("pdf-progress-percentText:" + uuid);
        percentText.expire(1, TimeUnit.DAYS);
        percentText.set("0%");

        try {
            for (int i = 0; i < samples.size(); i++) {
                Thread.sleep(100);
                getHtml(webDriver ,samples.get(i));
                sampleService.updateSample(samples.get(i));
                curCount.addAndGet(1);
                BigDecimal percentValue = new BigDecimal((i+1.0)/totalCount.get()).setScale(2,BigDecimal.ROUND_HALF_UP);
                percent.set(percentValue.doubleValue());
                percentText.set(percentValue.multiply(new BigDecimal(100)) + "%");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webDriver.close();
        }



    }



    private void getHtml(WebDriver webDriver, Sample sample) throws InterruptedException, AWTException, IOException {
        log.info("样本的url={}",sample.getUrl());
        webDriver.get(sample.getUrl());

        String currentWin = webDriver.getWindowHandle();

        Thread.sleep(1000);
        WebElement addpBtn = webDriver.findElement(By.className("addp"));
        addpBtn.click();

        Thread.sleep(1000);
        WebElement genLayoutBtn = webDriver.findElement(By.id("genLayout"));
        genLayoutBtn.click();

        currentWin = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (currentWin.equals(handle)) {
                continue;
            }
            webDriver = webDriver.switchTo().window(handle);
        }

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(7000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(1000);


        Runtime runtime = Runtime.getRuntime();
//        String exeDir = System.getProperty("user.dir") + "\\src\\main\\resources\\driver\\" + "pdf.exe";
        log.info(sample.getCode());
        String[] commandArray = {DriverPathUtils.getPath() + "pdf.exe", sample.getCode() + ".pdf"};
        runtime.exec(commandArray);

        Thread.sleep(3000);

        webDriver.close();
        webDriver.switchTo().window(currentWin);

    }


    public Progress progress(String uuid) {

        RAtomicLong totalCount = redisson.getAtomicLong("pdf-progress-totalCount:" + uuid);

        RAtomicLong curCount = redisson.getAtomicLong("pdf-progress-curCount:" + uuid);

        RAtomicDouble percent = redisson.getAtomicDouble("pdf-progress-percent:" + uuid);

        RBucket<String> percentText = redisson.getBucket("pdf-progress-percentText:" + uuid);

        Progress progress = new Progress();
        progress.setTotalCount(totalCount.get());
        progress.setCurCount(curCount.get());
        progress.setPercent(percent.get());
        progress.setPercentText(percentText.get());


        return progress;
    }
}
