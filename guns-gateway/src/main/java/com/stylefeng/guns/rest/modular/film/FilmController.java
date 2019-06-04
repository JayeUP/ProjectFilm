package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.film.FilmService;
import com.stylefeng.guns.rest.vo.FilmRequestVO;
import com.stylefeng.guns.rest.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    //影片条件列表查询请求
    @RequestMapping(value = "/getConditionList",method = RequestMethod.GET)
    public String getConditionList(@RequestParam(value = "catId",required = false,defaultValue = "99")String catId,
                                   @RequestParam(value = "sourceId",required = false,defaultValue = "99")String sourceId,
                                   @RequestParam(value = "yearId",required = false,defaultValue = "99")String yearId){

        return null;
    }

    @RequestMapping(value = "/getFilm", method = RequestMethod.GET)
    public ResponseVO getFilms(FilmRequestVO filmRequestVO) {
        switch (filmRequestVO.getShowType()) {
            case 1:

        }
        return new ResponseVO();
    }
}
