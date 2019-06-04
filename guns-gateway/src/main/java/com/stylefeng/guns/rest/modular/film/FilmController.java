package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.film.FilmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController {

    @RequestMapping("/test")
    public String getResult(){
        System.out.println("get rest!");
        return "ok";
    }

    @Reference
    FilmService filmService;

    @RequestMapping("/testFilm")
    public String testFilm(){
        return filmService.test();
    }

    //首页请求
    @RequestMapping(value = "/getIndex",method = RequestMethod.GET)
    public String getIndex(){
        return null;
    }
}
