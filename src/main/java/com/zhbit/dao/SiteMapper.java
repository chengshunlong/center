package com.zhbit.dao;

import com.zhbit.bean.Sites;
import com.zhbit.bean.Type;
import com.zhbit.bean.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-6:04
 */
public interface SiteMapper {
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

    //和type表联合查询
    List<Sites> querySiteWithTypeName();

    //和type表联合查询
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
