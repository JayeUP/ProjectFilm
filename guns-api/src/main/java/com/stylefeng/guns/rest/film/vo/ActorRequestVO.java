package com.stylefeng.guns.rest.film.vo;

import lombok.Data;

import java.util.List;

@Data
public class ActorRequestVO {
    private ActorVO director;
    private List<ActorVO> actors;
}
