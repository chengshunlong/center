package com.zhbit.dao;

import com.zhbit.bean.EOrders;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-23:32
 */
public interface EOrderMapper {

    //添加订单
    int addEOrder(EOrders eorders);

    //删除订单
    int deleteEOrder(int eoid);

    //根据toid删除器材订单
    int deleteEOrderByTOid(String toid);

    //更新订单
    int updateEOrders(EOrders eorders);

    //根据eoid查询订单
    EOrders queryEOrderByEOid(int eoid);

    //查询全部订单
    List<EOrders> queryAllEOrders();

    List<EOrders> queryAllEOrdersByTOid(String toid);


    //和eqps表联合查询（eorders需要eqpPrice）
    EOrders queryEOrderWithEqpsByEOid(int eoid);

    //和eqps表联合查询（eorders需要eqpPrice）
    List<EOrders> queryEOrderWithEqps();

}
