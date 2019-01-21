package com.onegene.server.selenium.entity;

import lombok.Data;

/**
 * @ClassName Progress
 * @Descriiption Progress进度条
 * @Author yanjiantao
 * @Date 2019/1/21 11:49
 **/
@Data
public class Progress {

    private long totalCount;
    private long curCount;
    private double percent;
    private String percentText;

}
