package com.zhbit.service;

import com.zhbit.bean.Eqps;
import com.zhbit.dao.EqpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-17:02
 */
@Service
public class EqpServiceImpl implements EqpService {

    @Autowired
    private EqpMapper eqpMapper;

    public int addEqp(Eqps eqps) {
        return eqpMapper.addEqp(eqps);
    }

    public int deleteEqp(int eid) {
        return eqpMapper.deleteEqp(eid);
    }

    public int updateEqp(Eqps eqps) {
        return eqpMapper.updateEqp(eqps);
    }

    public Eqps queryEqpByEid(int eid) {
        return eqpMapper.queryEqpByEid(eid);
    }

    public List<Eqps> queryAllEqp() {
        return eqpMapper.queryAllEqp();
    }

    public Eqps queryEqpByEqpName(String eqpName) {
        return eqpMapper.queryEqpByEqpName(eqpName);
    }

    public int updatePaymentEqp(Eqps eqps) {
        return eqpMapper.updatePaymentEqp(eqps);
    }

    public List<Eqps> searchEqp(String searchParam) {
        return eqpMapper.searchEqp(searchParam);
    }
}
