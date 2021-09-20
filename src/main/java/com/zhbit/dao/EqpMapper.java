package com.zhbit.dao;

import com.zhbit.bean.Eqps;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-16:52
 */
public interface EqpMapper {

    //添加器材
    int addEqp(Eqps eqps);

    //删除器材
    int deleteEqp(int eid);

    //更新器材
    int updateEqp(Eqps eqps);

    //查询器材
    Eqps queryEqpByEid(int eid);

    //查询全部器材
    List<Eqps> queryAllEqp();

    //根据器材名称查询场地信息
    Eqps queryEqpByEqpName(String eqpName);

    //提交付款更新数据
    int updatePaymentEqp(Eqps eqps);

    List<Eqps> searchEqp(String searchParam);

}
