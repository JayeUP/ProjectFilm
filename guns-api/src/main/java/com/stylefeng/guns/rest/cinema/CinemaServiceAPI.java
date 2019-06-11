package com.stylefeng.guns.rest.cinema;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.cinema.vo.*;

import java.util.List;

/**
 * @Author created by lziz
 * @Data 2019/6/4 22:16
 */
public interface CinemaServiceAPI {


    /***
     * 1.根据CinemaQueryVO, 查询影院列表
     * @param cinemaQueryVO 查询条件
     * @return Page<CinemaVO>
     */
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);


    /***
     * 2.根据条件获取品牌列表
     * @param brandId 影院编号
     * @return List<BrandVO>
     */
    List<BrandVO> getBrands(int brandId);


    /***
     * 3.获取行政区域列表
     * @param areaId 行政区
     * @return List<AreaVO>
     */
    List<AreaVO> getAreas(int areaId);


    /***
     * 4.获取影厅类型列表
     * @param hallType 影厅类型
     * @return List<HallTypeVO>
     */
    List<HallTypeVO> getHallTypes(int hallType);

    /**
     * 根据影院编号，获取影院信息
     * @param cinemaId 影院编号
     * @return CinemaInfoVO
     */
    CinemaInfoVO getCinemaInfoById(int cinemaId);

    /**
     * 根据影院id获取片场
     * @param cinemaId 影院编号
     * @return List<FilmInfoVO>
     */
    List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId);

    /**
     * 根据片场获取影片名
     * @param fieldId 片场
     * @return FilmInfoVO
     */
    FilmInfoVO getFilmInfoByFieldId(Integer fieldId);

    /**
     * 根据片场获取影厅
     * @param fieldId 片场
     * @return HallInfoVO
     */
    HallInfoVO getFilmFieldInfo(Integer fieldId);
}
