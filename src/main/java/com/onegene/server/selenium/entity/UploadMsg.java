package com.onegene.server.selenium.entity;

import lombok.Data;

/**
 * @ClassName UploadMsg
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/18 10:11
 **/
@Data
public class UploadMsg {

    private String value;

    public UploadMsg(String value) {
        this.value = value;
    }
}
