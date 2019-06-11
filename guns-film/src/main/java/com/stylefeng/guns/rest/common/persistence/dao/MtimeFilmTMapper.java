package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.film.vo.FilmDetailVO;
import com.stylefeng.guns.rest.film.vo.FilmInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author jy
 * @since 2019-06-04
 */
public interface MtimeFilmTMapper extends BaseMapper<MtimeFilmT> {

    List<FilmInfo> getTop100();

    List<FilmInfo> getExpectRanking();

    List<FilmInfo> getBoxRanking();

    int getHotFilmsNum();

    List<FilmInfo> getHotFilms();

    int getSoonFilmsNum();

    List<FilmInfo> getSoonFilms();

    FilmDetailVO getFilmDetailByName(@Param("filmName") String filmName);
    FilmDetailVO getFilmDetailById(@Param("uuid") String uuid);
}
