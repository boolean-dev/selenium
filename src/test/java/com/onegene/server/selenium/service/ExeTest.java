package com.onegene.server.selenium.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName ExeTest
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/17 13:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExeTest {

    @Test
    public void testExe() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String exeDir = System.getProperty("user.dir")+ "\\src\\main\\resources\\driver\\" + "pdf.exe";
        String[] commandArray = {exeDir, "test.pdf"};
        runtime.exec(commandArray);
    }


    @Test
    public void stringTest() throws IOException {
       String url = "http://report.1genehealth.com/static/report_adult_2018bx-V1/main.html?sampleCode=186091756432&v=2017083001";
       StringBuffer stringBuffer =new StringBuffer("http://report.1genehealth.com/static/report_adult_2018bx-V1/main.html?sampleCode=186091756432&v=2017083001");
       int index_index = stringBuffer.indexOf("/main.html");
       stringBuffer.replace(index_index,index_index+5, "_print/index");
        System.out.println(stringBuffer);
    }

    @Test
    public void testCmd() {
        String[] cmd = {"Notepad.exe","d:/test.txt"};
        Process process = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (process != null){
                process.destroy();
            }
        }
    }
}
