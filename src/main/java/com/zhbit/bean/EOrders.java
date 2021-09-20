package com.zhbit.bean;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-23:30
 */
public class EOrders {

    private int eoid;
    private int eid;
    private String toid;
    private String eqpName;
    private int eqpPrice;
    private int eqpAmount;
    private int totalPrice;

    private Eqps eqps;

    public Eqps getEqps() {
        return eqps;
    }

    public void setEqps(Eqps eqps) {
        this.eqps = eqps;
    }

    public EOrders() {
    }

    public EOrders(int eoid, int eid, String toid, String eqpName, int eqpPrice, int eqpAmount, int totalPrice) {
        this.eoid = eoid;
        this.eid = eid;
        this.toid = toid;
        this.eqpName = eqpName;
        this.eqpPrice = eqpPrice;
        this.eqpAmount = eqpAmount;
        this.totalPrice = totalPrice;
    }

    public int getEoid() {
        return eoid;
    }

    public void setEoid(int eoid) {
        this.eoid = eoid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "EOrders{" +
                "eoid=" + eoid +
                ", eid=" + eid +
                ", toid=" + toid +
                ", eqpName='" + eqpName + '\'' +
                ", eqpPrice=" + eqpPrice +
                ", eqpAmount=" + eqpAmount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}