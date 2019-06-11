package com.stylefeng.guns.rest.modular.cinema.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.cinema.CinemaServiceAPI;
import com.stylefeng.guns.rest.cinema.vo.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.MtimeAreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeBrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeCinemaT;
import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    //1、根据CinemaQueryVO，查询影院列表
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {

        //业务实体集合
        List<CinemaVO> cinemas = new ArrayList<>();

        Page<MtimeCinemaT> page = new Page<>(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());

        // 判断是否传入查询条件 -> brandId,distId,hallType 是否==99
        EntityWrapper<MtimeCinemaT> entityWrapper = new EntityWrapper<>();
        if (cinemaQueryVO.getBrandId() != 99){
            entityWrapper.eq("brand_id", cinemaQueryVO.getBrandId());
        }
        if (cinemaQueryVO.getDistrictId() != 99){
            entityWrapper.eq("area_id", cinemaQueryVO.getDistrictId());
        }
        if (cinemaQueryVO.getHallType() != 99){// %#3#%
            entityWrapper.like("hall_ids", "%#+" + cinemaQueryVO.getHallType() + "+#%");
        }

        // 将数据实体转换为业务实体
        List<MtimeCinemaT> mtimeCinemaTS = mtimeCinemaTMapper.selectPage(page, entityWrapper);
        for (MtimeCinemaT mtimeCinemaT : mtimeCinemaTS) {
            CinemaVO cinemaVO = new CinemaVO();

            cinemaVO.setUuid(mtimeCinemaT.getUuid() + "");
            cinemaVO.setCinemaName(mtimeCinemaT.getCinemaName() + "");
            cinemaVO.setAddress(mtimeCinemaT.getImgAddress() + "");
            cinemaVO.setMinimumPrice(mtimeCinemaT.getMinimumPrice() + "");

            cinemas.add(cinemaVO);
        }

        // 根据条件，判断影院列表总数
        long counts = mtimeCinemaTMapper.selectCount(entityWrapper);

        //组织返回值对象
        Page<CinemaVO> result = new Page<>();

        result.setRecords(cinemas);
        result.setSize(cinemaQueryVO.getPageSize());
        result.setTotal(counts);

        return result;
    }


    //2、根据条件获取品牌列表[除了就99以外，其他的数字为isActive]
    @Override
    public List<BrandVO> getBrands(int brandId) {

        boolean flag = false;

        List<BrandVO> brandVOS = new ArrayList<>();
        // 判断brandId是否存在
        MtimeBrandDictT mtimeBrandDictT = mtimeBrandDictTMapper.selectById(brandId);
        // 判断brandId 是否等于 99
        if (brandId == 99 || mtimeBrandDictT == null || mtimeBrandDictT.getUuid() == null){
            flag = true;
        }

        // 查询所有列表
        List<MtimeBrandDictT> mtimeBrandDictTS = mtimeBrandDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for (MtimeBrandDictT brand : mtimeBrandDictTS) {
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandName(brand.getShowName());
            brandVO.setBrandId(brand.getUuid() + "");
            if (flag){
                if (brand.getUuid() == 99){
                    brandVO.setActive(true);
                }
            }else {
                if (brand.getUuid() == brandId){
                    brandVO.setActive(true);
                }
            }
            brandVOS.add(brandVO);
        }
        return brandVOS;
    }

    //3、获取行政区域列表
    @Override
    public List<AreaVO> getAreas(int areaId) {

        boolean flag = false;
        List<AreaVO> areaVOS = new ArrayList<>();

        //判断areaId是否存在
        MtimeAreaDictT mtimeAreaDictT = mtimeAreaDictTMapper.selectById(areaId);
        // 判断areaId 是否等于 99
        if (areaId == 99 || mtimeAreaDictT == null || mtimeAreaDictT.getUuid() == null){
            flag = true;
        }

        // 查询所有列表
        List<MtimeAreaDictT> mtimeAreaDictTS = mtimeAreaDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for (MtimeAreaDictT areaDictT : mtimeAreaDictTS) {
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaName(areaDictT.getShowName());
            areaVO.setAreaId(areaDictT.getUuid() + "");
            if (flag){
                if (areaDictT.getUuid() == 99){
                    areaVO.setActive(true);
                }
            }else {
                if (areaDictT.getUuid() == areaId){
                    areaVO.setActive(true);
                }
            }
            areaVOS.add(areaVO);
        }

        return areaVOS;
    }

    //4、获取影厅类型列表
    @Override
    public List<HallTypeVO> getHallTypes(int hallType) {

        boolean flag = false;

        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        //判断halltype是否存在
        MtimeHallDictT mtimeHallDictT = mtimeHallDictTMapper.selectById(hallType);
        // 判断halltype是否等于 99
        if (hallType == 99 || mtimeHallDictT == null || mtimeHallDictT.getUuid() == 99){
            flag = true;
        }
        // 查询所有列表
        List<MtimeHallDictT> mtimeHallDictTS = mtimeHallDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for (MtimeHallDictT hallDictT : mtimeHallDictTS) {
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeName(hallDictT.getShowName());
            hallTypeVO.setHalltypeId(hallDictT.getUuid() + "");
            if (flag){
                if (hallType == 99){
                    hallTypeVO.setActive(true);
                }
            }else {
                if (hallDictT.getUuid() == 99){
                    hallTypeVO.setActive(true);
                }
            }
            hallTypeVOS.add(hallTypeVO);
        }

        return hallTypeVOS;
    }



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
