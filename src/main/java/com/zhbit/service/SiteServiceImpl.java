package com.zhbit.service;

import com.sun.org.apache.regexp.internal.RE;
import com.zhbit.bean.Sites;
import com.zhbit.bean.Type;
import com.zhbit.dao.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-6:18
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    SiteMapper siteMapper;

    public int addSite(Sites sites) {
        return siteMapper.addSite(sites);
    }

    public int deleteSite(int sid) {
        return siteMapper.deleteSite(sid);
    }

    public int updateSite(Sites sites) {
        return siteMapper.updateSite(sites);
    }

    public Sites querySiteBySid(int sid) {
        return siteMapper.querySiteBySid(sid);
    }

    public List<Sites> queryAllSite() {
        return siteMapper.queryAllSite();
    }

    public List<Sites> querySiteWithTypeName() {
        return siteMapper.querySiteWithTypeName();
    }

    public Sites querySiteWithtTypeNameBySid(int sid) {
        return siteMapper.querySiteWithtTypeNameBySid(sid);
    }

    public Sites querySiteBySiteName(String siteName) {
        return siteMapper.querySiteBySiteName(siteName);
    }

    public int queryTidByTypeName(String typeName) {
        return siteMapper.queryTidByTypeName(typeName);
    }

    public Type queryTypeNameByTid(int tid) {
        return siteMapper.queryTypeNameByTid(tid);
    }

    public List<Sites> searchSite(String searchParam) {
        return siteMapper.searchSite(searchParam);
    }

    public List<String> queryAllTypeName() {
        return siteMapper.queryAllTypeName();
    }


}
