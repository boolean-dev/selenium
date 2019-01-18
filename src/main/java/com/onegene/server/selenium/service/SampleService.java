package com.onegene.server.selenium.service;

import com.mongodb.MongoBulkWriteException;
import com.onegene.server.selenium.entity.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName SampleService
 * @Descriiption SampleService
 * @Author yanjiantao
 * @Date 2019/1/18 10:52
 **/

@Service
@Slf4j
public class SampleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Collection<Sample> samples) {

        try {
            mongoTemplate.insertAll(samples);
        } catch (MongoBulkWriteException e) {
            log.error("导入样本信息失败，msg={}",e.getMessage());

        }catch (Exception e) {
            log.error("导入样本信息失败，msg={}",e.getMessage());
        }
    }


    public List<Sample> findAll() {
        return mongoTemplate.findAll(Sample.class);
    }
}
