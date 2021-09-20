package com.zhbit.bean;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-14:53
 */
public class Type {
    private int tid;
    private String typeName;

    public Type() {
    }

    public Type(int tid, String typeName) {
        this.tid = tid;
        this.typeName = typeName;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
