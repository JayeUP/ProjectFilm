package com.stylefeng.guns.rest.cinema;

import com.stylefeng.guns.rest.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.cinema.vo.HallInfoVO;

import java.util.List;

/**
 * @Author created by lziz
 * @Data 2019/6/4 22:16
 */
public interface CinemaServiceAPI {

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
