package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.cinema.vo.HallInfoVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by lziz
 * 2019/6/5 16:13
 */
@RestController
@RequestMapping("/cinema")
@Slf4j
public class CinemaController {

    @Reference(interfaceClass = CinemaServiceAPI.class,connections = 10,
            cache = "lru",check = false)
    private CinemaServiceAPI cinemaServiceAPI;
    //图片地址前缀
    private static final String img_pre = "";

    @RequestMapping(value = "getFields")
    public ResponseVO getFields(Integer cinemaId) {
        try{
            //查找信息
            CinemaInfoVO cinemaInfoById = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            List<FilmInfoVO> filmInfoByCinemaId = cinemaServiceAPI.getFilmInfoByCinemaId(cinemaId);

            //提取查到的结果封装到cinemaFieldResponseVO
            CinemaFieldsResponseVO cinemaFieldResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmList(filmInfoByCinemaId);
            //
            return ResponseVO.success(img_pre,cinemaFieldResponseVO);
        }catch (Exception e){
            log.error("获取播放场次失败", e);
            return ResponseVO.serviceFail("获取播放场次失败");
        }
    }

    @PostMapping(value = "getFieldInfo")
    public ResponseVO getFieldInfo(Integer cinemaId, Integer fieldId) {
        try {
            //查找信息
            CinemaInfoVO cinemaInfoById = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfoByFieldId = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
            HallInfoVO filmFieldInfo = cinemaServiceAPI.getFilmFieldInfo(fieldId);

            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
            cinemaFieldResponseVO.setHallInfo(filmFieldInfo);

            return ResponseVO.success(img_pre, cinemaFieldResponseVO);
        } catch (Exception e) {
            log.error("获取选座信息失败", e);
            return ResponseVO.serviceFail("获取选座信息失败");
        }
    }

}
