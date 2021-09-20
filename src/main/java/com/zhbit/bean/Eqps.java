package com.zhbit.bean;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-16:50
 */
public class Eqps {

    private int eid;
    private String eqpName;
    private int eqpPrice;
    private int eqpAmount;
    private String eqpPicture;

    public Eqps() {
    }

    public Eqps(int eid, String eqpName, int eqpPrice, int eqpAmount, String eqpPicture) {
        this.eid = eid;
        this.eqpName = eqpName;
        this.eqpPrice = eqpPrice;
        this.eqpAmount = eqpAmount;
        this.eqpPicture = eqpPicture;
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

    @Override
    public String toString() {
        return "Eqps{" +
                "eid=" + eid +
                ", eqpName='" + eqpName + '\'' +
                ", eqpPrice='" + eqpPrice + '\'' +
                ", eqpAmount='" + eqpAmount + '\'' +
                ", eqpPicture='" + eqpPicture + '\'' +
                '}';
    }
}
