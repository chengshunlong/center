package com.zhbit.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-16-13:12
 */
public class Msg {

    private int code;

    private String msg;

    //用户返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<String, Object>();

    /**
     * 成功
     * @return
     */
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("成功");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg("失败");
        return result;
    }

    /*
    加数据
     */
    public Msg add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
