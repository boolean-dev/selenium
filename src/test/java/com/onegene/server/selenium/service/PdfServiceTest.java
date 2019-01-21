package com.onegene.server.selenium.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PdfServiceTest {

    @Autowired
    private PdfService pdfService;

    @Test
    public void pdfExport() throws InterruptedException, IOException, AWTException {
//        pdfService.pdfExport();
    }

    @Test
    public void listTest() {
        List<String> lists = new ArrayList<>();
        lists.add("111");
        lists.add("222");
        lists.add("333");
        lists.add("444");
        lists.add("555");

        List<String> newList = lists.stream().filter(list -> list.lastIndexOf("2") != -1).collect(Collectors.toList());
        newList.forEach(System.out::println);

    }

    @Test
    public void testList() {
        List<String> lists = new ArrayList<>();

        lists.addAll(new ArrayList<>());
        lists.forEach(System.out::println);
    }
}