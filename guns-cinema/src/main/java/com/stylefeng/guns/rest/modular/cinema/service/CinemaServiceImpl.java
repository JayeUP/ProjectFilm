package com.stylefeng.guns.rest.modular.cinema.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.rest.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.rest.cinema.vo.HallInfoVO;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author created by lziz
 * @Data 2019/6/4 22:32
 */
@Component
@Service(interfaceClass = CinemaServiceAPI.class,executes = 10)
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    MtimeCinemaTMapper mtimeCinemaTMapper;
    @Autowired
    MtimeFieldTMapper mtimeFieldTMapper;
    @Autowired
    MtimeAreaDictTMapper mtimeAreaDictTMapper;
    @Autowired
    MtimeBrandDictTMapper mtimeBrandDictTMapper;
    @Autowired
    MtimeHallDictTMapper mtimeHallDictTMapper;
    @Autowired
    MtimeHallFilmInfoTMapper mtimeHallFilmInfoTMapper;

    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        // 数据实体
        MtimeCinemaT mtimeCinemaT = mtimeCinemaTMapper.selectById(cinemaId);
        // 将数据实体转换成业务实体
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        cinemaInfoVO.setCinemaAddress(mtimeCinemaT.getCinemaAddress());
        cinemaInfoVO.setImgUrl(mtimeCinemaT.getImgAddress());
        cinemaInfoVO.setCinemaPhone(mtimeCinemaT.getCinemaPhone());
        cinemaInfoVO.setCinemaName(mtimeCinemaT.getCinemaName());
        cinemaInfoVO.setCinemaId(mtimeCinemaT.getBrandId() + "");
        return cinemaInfoVO;
    }

    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId) {
        List<FilmInfoVO> filmInfos = mtimeFieldTMapper.getFilmInfos(cinemaId);
        return filmInfos;
    }

    @Override
    public FilmInfoVO getFilmInfoByFieldId(Integer fieldId) {
        FilmInfoVO filmInfoVO = mtimeFieldTMapper.getFilmInfoById(fieldId);
        return filmInfoVO;
    }

    @Override
    public HallInfoVO getFilmFieldInfo(Integer fieldId) {
        HallInfoVO hallInfoVO = mtimeFieldTMapper.getHallInfo(fieldId);
        return hallInfoVO;
    }
}
