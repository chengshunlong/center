package com.zhbit.service;

import com.zhbit.bean.Orders;
import com.zhbit.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-19:45
 */
public interface OrderService {

    //添加订单
    int addOrder(Orders orders);

    //删除订单
    int deleteOrder(int oid);

    //更新订单
    int updateOrder(Orders orders);

    //根据oid查询订单
    Orders queryOrderByOid(int oid);

    //根据uid查询订单
    List<Orders> queryOrderByUid(int uid);

    //查询全部订单
    List<Orders> queryAllOrder();

    //用户租赁场地、生成一条订单
    int createOrder(Orders orders);

    //批准订单
    int approveSite(int oid);

    //模糊查询
    List<Orders> searchOrder(String searchParam);

}
