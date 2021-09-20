package com.zhbit.service;

import com.sun.xml.internal.bind.v2.TODO;
import com.zhbit.bean.TotalOrders;
import com.zhbit.dao.TotalOrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-19-19:52
 */
@Service
public class TotalOrdersServiceImpl implements TotalOrdersService {

    @Autowired
    TotalOrdersMapper totalOrdersMapper;

    public int addTotalOrder(TotalOrders totalOrders) {
        return totalOrdersMapper.addTotalOrder(totalOrders);
    }

    public int deleteTotalOrder(String toid) {
        return totalOrdersMapper.deleteTotalOrder(toid);
    }

    public int updateTotalOrder(TotalOrders totalOrders) {
        return totalOrdersMapper.updateTotalOrder(totalOrders);
    }

    public TotalOrders queryTotalOrderByTOid(String toid) {
        return totalOrdersMapper.queryTotalOrderByTOid(toid);
    }

    public List<TotalOrders> queryAllTotalOrders() {
        return totalOrdersMapper.queryAllTotalOrders();
    }

    public List<TotalOrders> queryAllTotalOrdersByUid(int uid) {
        return totalOrdersMapper.queryAllTotalOrdersByUid(uid);
    }

    public TotalOrders queryAllTotalOrdersWithEOrdersByTOid(String toid) {
        return totalOrdersMapper.queryAllTotalOrdersWithEOrdersByTOid(toid);
    }

    public List<TotalOrders> queryAllTotalOrdersWithEOrders() {
        return totalOrdersMapper.queryAllTotalOrdersWithEOrders();
    }

    public List<TotalOrders> queryAllTotalOrdersWithEOrdersPageHelper() {
        return totalOrdersMapper.queryAllTotalOrdersWithEOrdersPageHelper();
    }

    public List<TotalOrders> queryAllTotalOrdersWithEOrdersByTOidPageHelper(String toid) {
        return totalOrdersMapper.queryAllTotalOrdersWithEOrdersByTOidPageHelper(toid);
    }

    public List<TotalOrders> queryAllTotalOrdersWithEOrdersByUidPageHelper(int uid) {
        return totalOrdersMapper.queryAllTotalOrdersWithEOrdersByUidPageHelper(uid);
    }

    public List<TotalOrders> searchTotalOrders(String searchParam) {
        return totalOrdersMapper.searchTotalOrders(searchParam);
    }

}
