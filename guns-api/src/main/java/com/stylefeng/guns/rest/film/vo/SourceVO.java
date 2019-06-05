package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SourceVO implements Serializable {
    String sourceId;
    String sourceName;
    boolean isActive;
}
