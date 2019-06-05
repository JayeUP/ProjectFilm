package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerVO implements Serializable {
    String bannerId;
    String bannerAddress;
    String bannerUrl;
}
