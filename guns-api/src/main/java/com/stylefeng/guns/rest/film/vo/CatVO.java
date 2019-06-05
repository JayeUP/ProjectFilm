package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatVO implements Serializable {
    String catId;
    String catName;
    boolean isActive;
}
