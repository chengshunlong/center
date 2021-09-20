package com.zhbit.bean;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-19-17:58
 */
public class TotalOrders {
    private String toid;
    private int uid;
    private String userId;
    private String userName;
    private String userTel;
    private int totalPrice;
    private String status;
    private String time;

    public TotalOrders(String toid, int uid, String userId, String userName, String userTel, int totalPrice, String status, String time) {
        this.toid = toid;
        this.uid = uid;
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.totalPrice = totalPrice;
        this.status = status;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private List<EOrders> eOrders;

    public List<EOrders> geteOrders() {
        return eOrders;
    }

    public void seteOrders(List<EOrders> eOrders) {
        this.eOrders = eOrders;
    }

    public TotalOrders() {
    }


    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TotalOrders{" +
                "toid=" + toid +
                ", uid=" + uid +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
