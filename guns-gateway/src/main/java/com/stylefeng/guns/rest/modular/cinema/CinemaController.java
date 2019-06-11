package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.cinema.vo.*;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaConditionResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
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



    @RequestMapping(value = "getCinemas")
    public ResponseVO getCinemas(CinemaQueryVO cinemaQueryVO){

        try{
            // 按照五个条件进行筛选
            Page<CinemaVO> cinemas = cinemaServiceAPI.getCinemas(cinemaQueryVO);
            // 判断是否有满足条件的影院
            if(cinemas.getRecords() == null || cinemas.getRecords().size()==0){
                return ResponseVO.success("没有影院可查");
            }else{
                return ResponseVO.success(cinemas.getCurrent(),(int)cinemas.getPages(),"",cinemas.getRecords());
            }

        }catch (Exception e){
            // 如果出现异常，应该如何处理
            log.error("获取影院列表异常",e);
            return ResponseVO.serviceFail("查询影院列表失败");
        }
    }


    // 获取影院的查询条件
    @RequestMapping(value = "getCondition")
    public ResponseVO getCondition(CinemaQueryVO cinemaQueryVO){
        try{
            // 获取三个集合，然后封装成一个对象返回即可
            List<BrandVO> brands = cinemaServiceAPI.getBrands(cinemaQueryVO.getBrandId());
            List<AreaVO> areas = cinemaServiceAPI.getAreas(cinemaQueryVO.getDistrictId());
            List<HallTypeVO> hallTypes = cinemaServiceAPI.getHallTypes(cinemaQueryVO.getHallType());

            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreaList(areas);
            cinemaConditionResponseVO.setBrandList(brands);
            cinemaConditionResponseVO.setHalltypeList(hallTypes);

            return ResponseVO.success(cinemaConditionResponseVO);
        }catch (Exception e) {
            log.error("获取条件列表失败", e);
            return ResponseVO.serviceFail("获取影院查询条件失败");
        }
    }



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
