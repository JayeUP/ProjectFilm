package com.stylefeng.guns.rest.vo;

import lombok.Data;

/**
 * Created by HorseXInsect
 * 2019/6/4 22:40
 */
@Data
public class ResponseVO<D> {
    // 状态码(0表示成功， 1表示业务异常，999表示系统异常 )
    private int status;
    // 图片前缀
    private String imgPre;
    // 数据实体
    private D data;
    // 返回获取到的信息

    //返回信息
    private String msg;

    // 分页
    private int nowPage;
    private int totalPage;

    // 构造方法
    public ResponseVO() {}

    public static<M> ResponseVO success(int nowPage,int totalPage,String imgPre,M m){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        responseVO.setNowPage(nowPage);
        responseVO.setTotalPage(totalPage);
        return responseVO;
    }

    public static<M> ResponseVO success(String imgPre,M m){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);

        return responseVO;
    }

    public static<M> ResponseVO success(M m){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setData(m);

        return responseVO;
    }

    public static<M> ResponseVO success(String msg){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }

    public static<M> ResponseVO serviceFail(String msg){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);

        return responseVO;
    }

    public static<M> ResponseVO appFail(String msg){
        ResponseVO<Object> responseVO = new ResponseVO<>();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);

        return responseVO;
    }
}
