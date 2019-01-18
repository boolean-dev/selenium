package com.onegene.server.selenium.controller;

import com.onegene.server.selenium.entity.Sample;
import com.onegene.server.selenium.entity.UploadMsg;
import com.onegene.server.selenium.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName FileController
 * @Descriiption 表格上传
 * @Author yanjiantao
 * @Date 2019/1/17 20:32
 **/

@Controller
@Slf4j
public class FileController {

    @Autowired
    private SampleService sampleService;


    /**
     * 上传
     */
    @ResponseBody
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public Object upload(MultipartFile file, HttpServletResponse response, String type) throws IOException {



        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        Workbook workbook = new HSSFWorkbook(file.getInputStream());

        Set<Sample> sampleSet = new HashSet<>();
        Sheet sheet1 = workbook.getSheetAt(0);
        for (Row row : sheet1) {
            if (row.getRowNum() == 0) continue;
            List<String> rowData = this.getRowData(row);
            Sample sample = new Sample();
            sample.buildDefault(true);
            sample.setName(rowData.get(0));
            sample.setCode(rowData.get(1));
            StringBuffer url =new StringBuffer(rowData.get(2));
            int index = url.indexOf("/main.html");
            url.replace(index,index + 5, "_print/index");
            sample.setUrl(url.toString());
            sample.setCreateTime(new Date());
            sampleSet.add(sample);
        }

        sampleService.save(sampleSet);

        return new UploadMsg("success");
    }


    private List<String> getRowData(Row row) {
        List<String> rowData = new ArrayList<>();
        Cell cell = null;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            cell = row.getCell(i);
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    rowData.add(cell.getStringCellValue());
                    break;
                case BOOLEAN:
                    rowData.add(String.valueOf(cell.getBooleanCellValue()));
                    break;
                case NUMERIC:
                    rowData.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                    break;
                default:
                    rowData.add("");
                    break;
            }
        }

        return rowData;
    }
}
