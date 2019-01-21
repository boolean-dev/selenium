package com.onegene.server.selenium.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Sample
 * @Descriiption 样本数据
 * @Author yanjiantao
 * @Date 2019/1/18 10:20
 **/
@Data
@Document(collection = "tb_sample_pdf")
/*@CompoundIndexes({
        @CompoundIndex(name = "code_index", def = "{'code':1,'isExport':1}", unique = true)
})*/
public class Sample {

    protected static GlobalId globalId = new GlobalId(0L, 0L);

    @Id
    private String id;
    private String name;
    private String code;
    private String url;
    private boolean isExport;
    private Date createTime;

    public Sample() {

    }

    public void buildDefault(boolean force) {
        if (force || StringUtils.isNotEmpty(this.id)) {
            this.id = "SP" + globalId.nextId();
        }
        this.isExport = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sample sample = (Sample) o;
        return Objects.equals(code, sample.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), code);
    }
}
