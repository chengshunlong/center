package com.zhbit.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.Orders;
import com.zhbit.bean.Sites;
import com.zhbit.bean.Type;
import com.zhbit.bean.Users;
import com.zhbit.service.OrderService;
import com.zhbit.service.SiteService;
import com.zhbit.service.SiteServiceImpl;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-14:41
 */
@Controller
@RequestMapping("/site")
@Transactional
public class SiteController {

    @Autowired
    @Qualifier("siteServiceImpl")
    SiteService siteService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;


    @RequestMapping("/allSites")
    public String allSites(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo, Model model){
        PageHelper.startPage(pageNo,10);
        List<Sites> sites = siteService.querySiteWithTypeName();
        PageInfo<Sites> sitesPageInfo = new PageInfo<Sites>(sites, 5);
        model.addAttribute("pageInfo",sitesPageInfo);
        return "allSites";
    }




    @RequestMapping("/updateSiteUseful")
    public String updateSiteToUseful(int sid){
        Sites sites = siteService.querySiteBySid(sid);
        if(sites.getSiteStatus().equals("不可用")){
            sites.setSiteStatus("可用");
            siteService.updateSite(sites);
        } else {
            sites.setSiteStatus("不可用");
            siteService.updateSite(sites);
        }

        return "redirect:/site/allSites";
    }


    @RequestMapping("/deleteSite")
    public String deleteSite(int sid){
//        System.out.println(siteService.querySiteBySid(sid));
        siteService.deleteSite(sid);
        return "redirect:/site/allSites";
    }

    @RequestMapping("/searchSite")
    public String searchSite(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,String searchParam,Model model){
        PageHelper.startPage(pageNo,5);
        List<Sites> sites = siteService.searchSite(searchParam);
        PageInfo<Sites> sitesPageInfo = new PageInfo<Sites>(sites, 5);
        model.addAttribute("pageInfo",sitesPageInfo);
        return "allSites";
    }

//=====================================新增================================================================

    @RequestMapping("/toAddSite")
    public String toAddSite(Model model){
        //场地类型下拉列表
//        List<String> typeNames = siteService.queryAllTypeName();
        List<String> typeNames = new ArrayList<String>();
        typeNames.add("教室");
        typeNames.add("室外场地");
        typeNames.add("报告厅");
        typeNames.add("饭堂");
        model.addAttribute("typeNames",typeNames);
        //场地状态下拉列表
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("可用");
        strings.add("不可用");
        model.addAttribute("status",strings);
        return "addSite";
    }

    @RequestMapping("/adminAddSiteNameVerify")
    @ResponseBody
    public String adminAddSiteNameVerify(String siteName,HttpSession session){
        String msg = "";
        if(siteName.length() > 16 || siteName.length() < 2){
            msg = "场地名称长度为2-16位，不能包含特殊字符";
        }else{
            for (int i = 0; i < siteName.length(); i++) {
                if((BeanUtils.isChinese(siteName.charAt(i)) == true || BeanUtils.isEnglishAlphabet(siteName) == true || BeanUtils.isDigit(siteName) == true) && BeanUtils.isSpecialChar(siteName) == false){
                    msg = "场地名称合法";
                }else{
                    msg = "场地名称不能包含特殊字符";
                    break;
                }
            }
        }
        Sites adminUpdateSite = (Sites) session.getAttribute("adminUpdateSite");
        if(siteName.length() == 0){
            msg = "";
        }
        return msg;
    }


    @RequestMapping("/addSite")
    public String addSite(Sites sites,HttpSession session,Model model){
        if(adminAddSiteNameVerify(sites.getSiteName(), session).equals("场地名称合法")){
            //        System.out.println(sites);
            //提交的场地类型
            String typeName = sites.getType().getTypeName();
            int tid = siteService.queryTidByTypeName(typeName);
            sites.setTid(tid);
            siteService.addSite(sites);
            return "redirect:/site/allSites";
        }else{
            model.addAttribute("errorMsg","添加失败！请检查输入数据是否合法");
            List<String> typeName = siteService.queryAllTypeName();
            model.addAttribute("typeNames",typeName);
            List<String> siteStatus = new ArrayList<String>();
            siteStatus.add("可用");
            siteStatus.add("不可用");
            model.addAttribute("status",siteStatus);
            return "addSite";
        }

    }



//=====================================更新===================================================================

    @RequestMapping("/toUpdateSite")
    public String toUpdateSite(int sid,Model model,HttpSession session){
        Sites sites = siteService.querySiteWithtTypeNameBySid(sid);
        session.setAttribute("adminUpdateSite",sites);
        //查出的sites中不包含type属性，无法实现数据回显 需要额外查出typeName
        //或者使用联表查询
//        Type type = siteService.queryTypeNameByTid(sites.getTid());
//        sites.setType(type);
        model.addAttribute("queriedSite",sites);
        //场地类型的下拉列表
//        List<String> typeNames = siteService.queryAllTypeName();
        List<String> typeNames = new ArrayList<String>();
        typeNames.add("教室");
        typeNames.add("室外场地");
        typeNames.add("报告厅");
        typeNames.add("饭堂");
        model.addAttribute("typeNames",typeNames);
        //场地状态下拉列表
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("可用");
        strings.add("不可用");
        model.addAttribute("status",strings);
        return "updateSite";
    }

    @RequestMapping("/adminUpdateSiteNameVerify")
    @ResponseBody
    public String adminUpdateSiteNameVerify(String siteName, HttpSession session){
        String msg = "";
        if(siteName.length() > 16 || siteName.length() < 2){
            msg = "场地名称长度为2-16位，不能包含特殊字符";
        }else{
            for (int i = 0; i < siteName.length(); i++) {
                if((BeanUtils.isChinese(siteName.charAt(i)) == true || BeanUtils.isEnglishAlphabet(siteName) == true || BeanUtils.isDigit(siteName) == true) && BeanUtils.isSpecialChar(siteName) == false){
                    msg = "场地名称合法";
                }else{
                    msg = "场地名称不能包含特殊字符";
                    break;
                }
            }
        }
        Sites adminUpdateSite = (Sites) session.getAttribute("adminUpdateSite");
        if(adminUpdateSite.getSiteName().equals(siteName)){
            msg = "";
        }
        if(siteName.length() == 0){
            msg = "";
        }
        return msg;
    }

//    @ResponseBody
//    @RequestMapping("/adminUpdateSitePictureVerify")
//    public String adminUpdateSitePictureVerify(String sitePicture,HttpSession session){
//        String msg = "";
//        if(sitePicture.length() > 40 || sitePicture.length() < 6){
//            msg = "场地图片长度为6-40位";
//        }else{
//            msg = "场地图片合法";
//        }
//        Sites adminUpdateSite = (Sites) session.getAttribute("adminUpdateSite");
//        if(adminUpdateSite.getSitePicture().equals(sitePicture)){
//            msg = "";
//        }
//        if(sitePicture.length() == 0){
//            msg = "";
//        }
//        return msg;
//    }

    @RequestMapping("/updateSite")
    public String updateSite(Sites sites, Model model,HttpSession session){
        if((adminUpdateSiteNameVerify(sites.getSiteName(),session).equals("场地名称合法") || adminUpdateSiteNameVerify(sites.getSiteName(),session).equals(""))
                /*&& (adminUpdateSitePictureVerify(sites.getSitePicture(),session).equals("场地图片合法") || adminUpdateSitePictureVerify(sites.getSitePicture(),session).equals(""))*/){
            //        System.out.println(sites);
            //根据获取的site的type属性中的typeName属性查询出对应的tid，进行修改
            String typeName = sites.getType().getTypeName();
            int tid = siteService.queryTidByTypeName(typeName);
            sites.setTid(tid);
            System.out.println(sites);
            System.out.println(sites.getType());
            siteService.updateSite(sites);
            session.removeAttribute("adminUpdateSite");
            return "redirect:/site/allSites";
        }else{
            List<String> typeNames = siteService.queryAllTypeName();
            model.addAttribute("typeNames",typeNames);
            List<String> siteStatus = new ArrayList<String>();
            siteStatus.add("可用");
            siteStatus.add("不可用");
            model.addAttribute("status",siteStatus);
            model.addAttribute("queriedSite",sites);
            model.addAttribute("errorMsg","修改失败！请检查输入的数据是否合法");
            return "updateSite";
        }

    }






    @RequestMapping("/userAllSites")
    public String userAllSites(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model){
        PageHelper.startPage(pageNo,10);
        List<Sites> sites = siteService.querySiteWithTypeName();
        PageInfo<Sites> sitesPageInfo = new PageInfo<Sites>(sites, 5);
        model.addAttribute("pageInfo",sitesPageInfo);
        return "userSiteLease";
    }


//    @ExceptionHandler(value = DataIntegrityViolationException.class)
//    public ModelAndView dataIntegrityViolationException(Exception exception){
//        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
//        modelAndView.addObject("exceptionInfo",exception);
//        modelAndView.addObject("msg","该场地存在订单，请先处理订单");
//        return modelAndView;
//    }


}