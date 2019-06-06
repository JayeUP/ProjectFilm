package com.stylefeng.guns.rest.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * @author Nemo
 * @version 1.0
 * @date 2019/6/6
 */
public class UserT extends Model<UserT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    private int uuid;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户性别 0-男 1-女
     */
    private int userSex;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户住址
     */
    private String address;

    /**
     * 头像URL
     */
    private String headUrl;

    /**
     * 个人介绍
     */
    private String biography;

    /**
     * 生活状态 0-单身，1-热恋中，2-已婚，3-为人父母
     */
    private int lifeState;

    /**
     * 创建时间
     */
    private String beginTime;

    /**
     * 修改时间
     */
    private String updateTime;

    public UserT() {
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    public UserT(int uuid, String userName, String userPwd, String nickName, int userSex, String birthday, String email, String userPhone, String address, String headUrl, String biography, int lifeState, String beginTime, String updateTime) {
        this.uuid = uuid;
        this.userName = userName;
        this.userPwd = userPwd;
        this.nickName = nickName;
        this.userSex = userSex;
        this.birthday = birthday;
        this.email = email;
        this.userPhone = userPhone;
        this.address = address;
        this.headUrl = headUrl;
        this.biography = biography;
        this.lifeState = lifeState;
        this.beginTime = beginTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FilmUser{" +
                "uuid=" + uuid +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userSex=" + userSex +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", address='" + address + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", biography='" + biography + '\'' +
                ", lifeState=" + lifeState +
                ", beginTime='" + beginTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getLifeState() {
        return lifeState;
    }

    public void setLifeState(int lifeState) {
        this.lifeState = lifeState;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}