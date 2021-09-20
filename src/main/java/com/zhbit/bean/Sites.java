package com.zhbit.bean;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-6:01
 */
public class Sites {

    private int sid;
    private String siteName;
    private String sitePicture;
    private String siteStatus;
    private int tid;

    //联合查询
    private Type type;

    public Sites(int sid, String siteName, String sitePicture, String siteStatus, int tid, Type type) {
        this.sid = sid;
        this.siteName = siteName;
        this.sitePicture = sitePicture;
        this.siteStatus = siteStatus;
        this.tid = tid;
        this.type = type;
    }

    public Sites() {
    }

    public Sites(int sid, String siteName, String sitePicture, String siteStatus, int tid) {
        this.sid = sid;
        this.siteName = siteName;
        this.sitePicture = sitePicture;
        this.siteStatus = siteStatus;
        this.tid = tid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSitePicture() {
        return sitePicture;
    }

    public void setSitePicture(String sitePicture) {
        this.sitePicture = sitePicture;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Sites{" +
                "sid=" + sid +
                ", siteName='" + siteName + '\'' +
                ", sitePicture='" + sitePicture + '\'' +
                ", siteStatus='" + siteStatus + '\'' +
                ", tid='" + tid + '\'' +
                '}';
    }
}
