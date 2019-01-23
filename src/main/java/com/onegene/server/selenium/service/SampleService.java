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

    /**
     * 保存样本信息
     * excel导入保存所有样本信息，不去重
     * @param samples   the sample list
     */
    public void save(Collection<Sample> samples) {

        try {
            mongoTemplate.insertAll(samples);
        } catch (MongoBulkWriteException e) {
            log.error("导入样本信息失败，msg={}",e.getMessage());

        }catch (Exception e) {
            log.error("导入样本信息失败，msg={}",e.getMessage());
        }
    }


    /**
     * 查找所有的样本
     * @return  the all sample list
     */
    public List<Sample> findAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isExport").is(false));
        return mongoTemplate.find(query,Sample.class);
    }

    /**
     * list-分页
     * 查找所有的sample信息，并且分页
     * @param pageable  分页参数
     * @return     查找所有的sample信息，并且分页
     */
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

    /**
     * 更新样本信息
     * 如果已经导出的sample，则更新
     * @param sample    the sample
     */
    public void updateSample(Sample sample) {
        Update update = new Update();
        update.set("isExport", true);
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(sample.getId())), update, Sample.class);
    }

    /**
     * 清楚样本报告
     * 清楚所有的样本报告，isExport置为trie
     */
    public void clean() {
        Update update = new Update();
        update.set("isExport", true);
        mongoTemplate.updateMulti(new Query(Criteria.where("isExport").is(false)), update, Sample.class);
    }
}
