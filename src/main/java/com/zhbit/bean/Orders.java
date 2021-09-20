package com.zhbit.bean;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-19:16
 */
public class Orders {
    private int oid;
    private int uid;
    private int sid;
    private String userName;
    private String siteName;
    private String status;

    public Orders() {
    }

    public Orders(int oid, int uid, int sid, String userName, String siteName, String status) {
        this.oid = oid;
        this.uid = uid;
        this.sid = sid;
        this.userName = userName;
        this.siteName = siteName;
        this.status = status;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", sid=" + sid +
                ", userName='" + userName + '\'' +
                ", siteName='" + siteName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
