package com.zhbit.dao;

import com.zhbit.bean.TotalOrders;

import java.util.List;
import java.util.Queue;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-19-19:36
 */
public interface TotalOrdersMapper {

    int addTotalOrder(TotalOrders totalOrders);

    int deleteTotalOrder(String toid);

    int updateTotalOrder(TotalOrders totalOrders);

    TotalOrders queryTotalOrderByTOid(String toid);

    List<TotalOrders> queryAllTotalOrders();

    List<TotalOrders> queryAllTotalOrdersByUid(int uid);

    TotalOrders queryAllTotalOrdersWithEOrdersByTOid(String toid);

    //联EOrders表查全部
    List<TotalOrders> queryAllTotalOrdersWithEOrders();

    //Pagehelper不支持嵌套结果集映射 使用分步查询
    List<TotalOrders> queryAllTotalOrdersWithEOrdersPageHelper();

    List<TotalOrders> queryAllTotalOrdersWithEOrdersByTOidPageHelper(String toid);

    //用户个人订单查询
    List<TotalOrders> queryAllTotalOrdersWithEOrdersByUidPageHelper(int uid);

    //模糊查询
    List<TotalOrders> searchTotalOrders(String searchParam);
}
