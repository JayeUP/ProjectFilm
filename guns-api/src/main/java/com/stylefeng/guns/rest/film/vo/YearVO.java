package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class YearVO implements Serializable {
    String yearId;
    String yearName;
    boolean isActive;
}
