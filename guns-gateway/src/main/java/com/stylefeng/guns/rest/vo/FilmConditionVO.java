package com.stylefeng.guns.rest.vo;

import com.stylefeng.guns.rest.film.vo.CatVO;
import com.stylefeng.guns.rest.film.vo.SourceVO;
import com.stylefeng.guns.rest.film.vo.YearVO;
import lombok.Data;

import java.util.List;

@Data
public class FilmConditionVO {
    List<CatVO> cats;
    List<SourceVO> sources;
    List<YearVO> years;
}
