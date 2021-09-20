package com.zhbit.service;

import com.zhbit.bean.EOrders;
import com.zhbit.dao.EOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-23:44
 */
@Service("EOrderServiceImpl")
public class EOrderServiceImpl implements EOrderService {

    @Autowired
    EOrderMapper eOrderMapper;


    public int addEOrder(EOrders eorders) {
        return eOrderMapper.addEOrder(eorders);
    }

    public int deleteEOrder(int eoid) {
        return eOrderMapper.deleteEOrder(eoid);
    }

    public int deleteEOrderByTOid(String toid) {
        return eOrderMapper.deleteEOrderByTOid(toid);
    }

    public int updateEOrders(EOrders eorders) {
        return eOrderMapper.updateEOrders(eorders);
    }

    public EOrders queryEOrderByEOid(int eoid) {
        return eOrderMapper.queryEOrderByEOid(eoid);
    }

    public List<EOrders> queryAllEOrders() {
        return eOrderMapper.queryAllEOrders();
    }

    public List<EOrders> queryAllEOrdersByTOid(String toid) {
        return eOrderMapper.queryAllEOrdersByTOid(toid);
    }


}
