/*
 * User  1.0  2018-11-29
 */
package com.github.hibernate.helper.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 用户表
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-29 zhuyan 初版
 */
@Entity
@Table(name = "sch_user", schema = "example_db", catalog = "")
public class User {
    private String userId;
    private Date userBirthday;
    private String userCode;
    private String userName;
    private String userPassword;
    private String userSex;
    private String userPhone;
    private String userState;
    private String userEmail;
    private String userIcard;
    private String userDesc;
    private String userRemark;
    private Date createTime;
    private String createUserId;
    private int flag;
    private Date updateTime;
    private String updateUserId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "flag")
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Column(name = "user_birthday")
    @Temporal(TemporalType.DATE)
    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Basic
    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "user_desc")
    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Basic
    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_icard")
    public String getUserIcard() {
        return userIcard;
    }

    public void setUserIcard(String userIcard) {
        this.userIcard = userIcard;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_remark")
    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    @Basic
    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_state")
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return flag == user.flag &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(createUserId, user.createUserId) &&
                Objects.equals(updateTime, user.updateTime) &&
                Objects.equals(updateUserId, user.updateUserId) &&
                Objects.equals(userBirthday, user.userBirthday) &&
                Objects.equals(userCode, user.userCode) &&
                Objects.equals(userDesc, user.userDesc) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userIcard, user.userIcard) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userPhone, user.userPhone) &&
                Objects.equals(userRemark, user.userRemark) &&
                Objects.equals(userSex, user.userSex) &&
                Objects.equals(userState, user.userState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, createTime, createUserId, flag, updateTime, updateUserId, userBirthday, userCode, userDesc, userEmail, userIcard, userName, userPassword, userPhone, userRemark, userSex, userState);
    }
}
