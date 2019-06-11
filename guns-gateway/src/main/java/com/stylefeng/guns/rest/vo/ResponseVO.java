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

    public static<D> ResponseVO success(int nowPage, int totalPage, String imgPre, D data) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(data);
        responseVO.setImgPre(imgPre);
        responseVO.setTotalPage(totalPage);
        responseVO.setNowPage(nowPage);
        return responseVO;
    }

    /**
     * 业务异常返回的消息
     */
    public static <D> ResponseVO serviceFail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }

    /**
     * 业务成功响应的消息
     */
    public static<D> ResponseVO success(String imgPre,D d){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(d);
        responseVO.setImgPre(imgPre);

        return responseVO;
    }

    public static<D> ResponseVO success(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }

    public static<D> ResponseVO success(D d){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(d);

        return responseVO;
    }
}
