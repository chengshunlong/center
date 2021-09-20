package com.zhbit.service;

import com.zhbit.bean.EOrders;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-23:44
 */
public interface EOrderService {

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

}
