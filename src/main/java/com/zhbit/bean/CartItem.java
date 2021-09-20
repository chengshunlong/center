package com.zhbit.bean;

/**
 * @Description 购物车项
 * @Author livestrong
 * @Date 2021-05-11-11:27
 */
public class CartItem {

    int eid;
    String eqpName;
    int eqpPrice;
    int eqpAmount;
    String eqpPicture;
    int totalPrice;


    public CartItem() {
    }

    public CartItem(int eid, String eqpName, int eqpPrice, int eqpAmount, String eqpPicture, int totalPrice) {
        this.eid = eid;
        this.eqpName = eqpName;
        this.eqpPrice = eqpPrice;
        this.eqpAmount = eqpAmount;
        this.eqpPicture = eqpPicture;
        this.totalPrice = totalPrice;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEqpName() {
        return eqpName;
    }

    public void setEqpName(String eqpName) {
        this.eqpName = eqpName;
    }

    public int getEqpPrice() {
        return eqpPrice;
    }

    public void setEqpPrice(int eqpPrice) {
        this.eqpPrice = eqpPrice;
    }

    public int getEqpAmount() {
        return eqpAmount;
    }

    public void setEqpAmount(int eqpAmount) {
        this.eqpAmount = eqpAmount;
    }

    public String getEqpPicture() {
        return eqpPicture;
    }

    public void setEqpPicture(String eqpPicture) {
        this.eqpPicture = eqpPicture;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "eid=" + eid +
                ", eqpName='" + eqpName + '\'' +
                ", eqpPrice=" + eqpPrice +
                ", eqpAmount=" + eqpAmount +
                ", eqpPicture='" + eqpPicture + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
