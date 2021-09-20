package com.zhbit.service;

import com.zhbit.bean.Sites;
import com.zhbit.bean.Type;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-6:18
 */
public interface SiteService {
    //添加场地
    int addSite(Sites sites);

    //删除场地
    int deleteSite(int sid);

    //更新场地
    int updateSite(Sites sites);

    //查询场地
    Sites querySiteBySid(int sid);

    //查询全部场地
    List<Sites> queryAllSite();

    //和type联表查 一对一
    List<Sites> querySiteWithTypeName();

    //和type表联查 一对一
    Sites querySiteWithtTypeNameBySid(int sid);

    //根据场地名称查询场地信息(sid)
    Sites querySiteBySiteName(String siteName);

    //根据类型名查类型id
    int queryTidByTypeName(String typeName);

    //根据类型tid查询typeName
    Type queryTypeNameByTid(int tid);

    List<Sites> searchSite(String searchParam);

    //查询所有场地名称
    List<String> queryAllTypeName();
}
