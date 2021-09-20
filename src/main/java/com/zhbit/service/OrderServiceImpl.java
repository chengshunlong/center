package com.zhbit.service;

import com.zhbit.bean.Orders;
import com.zhbit.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-19:46
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;


    public int addOrder(Orders orders) {
        return orderMapper.addOrder(orders);
    }

    public int deleteOrder(int oid) {
        return orderMapper.deleteOrder(oid);
    }

    public int updateOrder(Orders orders) {
        return orderMapper.updateOrder(orders);
    }

    public Orders queryOrderByOid(int oid) {
        return orderMapper.queryOrderByOid(oid);
    }

    public List<Orders> queryOrderByUid(int uid) {
        return orderMapper.queryOrderByUid(uid);
    }

    public List<Orders> queryAllOrder() {
        return orderMapper.queryAllOrder();
    }

    public int createOrder(Orders orders) {
        return orderMapper.createOrder(orders);
    }

    public int approveSite(int oid) {
        return orderMapper.approveSite(oid);
    }

    public List<Orders> searchOrder(String searchParam) {
        return orderMapper.searchOrder(searchParam);
    }
}
