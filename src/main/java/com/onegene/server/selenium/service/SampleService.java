package com.onegene.server.selenium.service;

import com.mongodb.MongoBulkWriteException;
import com.onegene.server.selenium.entity.Page;
import com.onegene.server.selenium.entity.Pageable;
import com.onegene.server.selenium.entity.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Query query = new Query();
        query.addCriteria(Criteria.where("isExport").is(false));
        return mongoTemplate.find(query,Sample.class);
    }

    public Page<Sample> findPage(Pageable pageable) {


        Query query = new Query();
        query.addCriteria(Criteria.where("isExport").is(false));
        query.skip(pageable.getPageSize() * (pageable.getPageNumber()-1)).limit(pageable.getPageSize());
        List<Sample> samples = mongoTemplate.find(query,Sample.class);

        if (samples == null || samples.size() == 0) {
            return new Page<Sample>(new ArrayList<>(), 0, pageable);
        } else {
            return new Page<Sample>(samples, mongoTemplate.count(new Query(Criteria.where("isExport").is(false)),Sample.class), pageable);
        }

    }

    public void updateSample(Sample sample) {
        Update update = new Update();
        update.set("isExport", true);
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(sample.getId())), update, Sample.class);
    }
}
