package com.onegene.server.selenium.utils;

import com.pdfcrowd.Pdfcrowd;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SeleniumUtilsTest {

    @Test
    public void getDownloadFilepath() {
    }

    @Test
    public void setTimeOut() {
    }

    @Test
    public void openAccess() throws IOException {

        WebDriver webDriver = SeleniumUtils.openAccess();
        webDriver.get("http://report.1genehealth.com/static/report_adult_2018bx-V1_print/index.html?sampleCode=189874042103&tdsourcetag=s_pctim_aiomsg");
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("http://report.1genehealth.com/static/report_adult_2018bx-V1_print/index.html?sampleCode=189874042103&tdsourcetag=s_pctim_aiomsg");
        log.info(webDriver.toString());
        WebElement addpBtn = webDriver.findElement(By.className("addp"));
        addpBtn.click();
        WebElement genLayoutBtn = webDriver.findElement(By.id("genLayout"));
        genLayoutBtn.click();

        String currentWin = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        WebDriver newWindow = null;
//        webDriver.close();
        for (String handle : handles) {
            if (currentWin.equals(handle)) continue;
            newWindow = webDriver.switchTo().window(handle);
        }

        String html = newWindow.getPageSource();
        newWindow.close();

        StringBuffer stringBuffer = new StringBuffer(html);
//        html.to
        int index = 0;
        while (index != -1) {
            index = stringBuffer.indexOf("<p class=\"wencont blockcenter block normal\" data-header=",index);
            if (index == -1) break;
            index = stringBuffer.indexOf("<div>", index);
            stringBuffer.replace(index,index+5, "<div class=\"wencont blockcenter block normal \" style=\"text-indent: 2em;\">");
            index = stringBuffer.indexOf("<div>", index);
            stringBuffer.replace(index,index+5, "<div class=\"wencont blockcenter block normal \" style=\"text-indent: 2em;\">");
        }
        log.info(stringBuffer.toString());




//        log.info(newWindow.toString());
//        String html = (String) driver_js.executeAsyncScript("return document.documentElement.outerHTML");
//        log.info(html);

        try {
            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient("booleandev", "fa1e3228eb5f306e286d723fd2689649");
//            Pdfcrowd.HtmlToPdfClient

            // run the conversion and write the result to a file
            client.convertStringToFile(html,"D:example.pdf");
//            client.convertUrlToFile(newWindow.getCurrentUrl(), "D:example.pdf");
        }
        catch(Pdfcrowd.Error why) {
            // report the error
            System.err.println("Pdfcrowd Error: " + why);

            // handle the exception here or rethrow and handle it at a higher level
            throw why;
        }
        catch(IOException why) {
            // report the error
            System.err.println("IO Error: " + why.getMessage());

            // handle the exception here or rethrow and handle it at a higher level
            throw why;
        }
    }


    @Test
    public void StringTest() {
        StringBuffer stringBuffer = new StringBuffer("<div data-header=\"常见肿瘤项目\" class=\"col1_t fs12 Wblock blockcenter block normal\">\n" +
                "          <img src=\"./img/%C3%A6%C2%96%C2%87%C3%A7%C2%8C%C2%AE/icons_07.png\" alt=\"\" /> <span>简介</span>\n" +
                "        </div>\n" +
                "        <p class=\"wencont blockcenter block normal\" data-header=\"常见肿瘤项目\" style=\"text-indent: 7mm;\"></p>\n" +
                "        <div>\n" +
                "          肝癌是肿瘤细胞起源于肝部的癌症，是死亡率仅次于肺癌的常见恶性肿瘤。最新统计数据显示，2014年中国肝癌新发36.5万例，死亡31.9万例，农村居民肝癌发病率高于城市居民。肝癌的发生与多种因素相关，包括：乙肝或丙肝病毒感染、疾病因素、肥胖、饮酒等。\n" +
                "        </div>\n" +
                "        <div>\n" +
                "          研究表明，KIF1B、STAT4、HLA-DQA1等基因的多态性和肝癌的发病风险密切相关。如KIF1B基因编码驱动蛋白家族成员1B蛋白，其功能是运输线粒体和突触囊泡前体。KIF1B参与调节细胞的迁移、侵袭、增殖、凋亡等和肿瘤发生发展密切相关的过程，基于大规模人群的研究表明，KIF1B基因的多态性和肝癌的发病风险密切相关。\n" +
                "        </div>\n" +
                "        <p></p>");
        int index = 0;
        while (index != -1) {
            index = stringBuffer.indexOf("<p class=\"wencont blockcenter block normal\" data-header=",index);
            if (index == -1) break;
            index = stringBuffer.indexOf("<div>", index);
            stringBuffer.replace(index,index+5, "<div class=\"wencont blockcenter block normal \" style=\"text-indent: 2em;\">");
            index = stringBuffer.indexOf("<div>", index);
            stringBuffer.replace(index,index+5, "<div class=\"wencont blockcenter block normal \" style=\"text-indent: 2em;\">");
        }
        log.info(stringBuffer.toString());
    }

    /*public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.CTRL_DOWN_MASK);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.CTRL_DOWN_MASK);
    }*/
}