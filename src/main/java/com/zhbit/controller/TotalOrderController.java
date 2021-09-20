package com.zhbit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.EOrders;
import com.zhbit.bean.TotalOrders;
import com.zhbit.bean.Users;
import com.zhbit.service.EOrderService;
import com.zhbit.service.TotalOrdersService;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-20-1:20
 */
@Controller
@RequestMapping("/totalorder")
public class TotalOrderController {

    @Autowired
    TotalOrdersService totalOrdersService;
    @Autowired
    EOrderService eOrderService;



    @RequestMapping("/allTotalOrders")
    public String allTotalOrders(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model){
            PageHelper.startPage(pageNo,5);
            List<TotalOrders> totalOrders = totalOrdersService.queryAllTotalOrdersWithEOrdersPageHelper();
            PageInfo<TotalOrders> totalOrdersPageInfo = new PageInfo<TotalOrders>(totalOrders, 5);
            model.addAttribute("pageInfo",totalOrdersPageInfo);
            return "allTotalOrders";
    }

    @RequestMapping("/searchTotalOrders")
    public String searchTotalOrder(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,String searchParam,Model model){
        PageHelper.startPage(pageNo,5);
        List<TotalOrders> totalOrders = totalOrdersService.searchTotalOrders(searchParam);
        PageInfo<TotalOrders> totalOrdersPageInfo = new PageInfo<TotalOrders>(totalOrders, 5);
        model.addAttribute("pageInfo",totalOrdersPageInfo);
        model.addAttribute("msg","searchTotalOrders");
        return "allTotalOrders";
    }

    @RequestMapping("/deleteTotalOrder")
    public String deleteTotalOrder(String toid){
        //先删除以此toid为外键的eorders器材订单
        eOrderService.deleteEOrderByTOid(toid);
        totalOrdersService.deleteTotalOrder(toid);
        return "redirect:/totalorder/allTotalOrders";
    }


    @RequestMapping("/toUserAllTotalOrders")
    public String toUserAllTotalOrders(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model, HttpSession session){
        Users user = (Users) session.getAttribute("user");
        PageHelper.startPage(pageNo,5);
        List<TotalOrders> totalOrders = totalOrdersService.queryAllTotalOrdersWithEOrdersByUidPageHelper(user.getUid());
        PageInfo<TotalOrders> totalOrdersPageInfo = new PageInfo<TotalOrders>(totalOrders, 5);
        model.addAttribute("pageInfo",totalOrdersPageInfo);
        return "userAllTotalOrders";
    }

    @RequestMapping("/toUpdateTotalOrder")
    public String toUpdateTotalOrder(String toid,Model model,HttpSession session){
        TotalOrders totalOrders = totalOrdersService.queryTotalOrderByTOid(toid);
        model.addAttribute("queriedOrders",totalOrders);
        //订单状态
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("已付款");
        statusList.add("未付款");
        model.addAttribute("statusList",statusList);
        session.setAttribute("adminUpdateTotalOrder", totalOrders);
        return "updateTotalOrder";
    }

    @ResponseBody
    @RequestMapping("/totalPriceVerify")
    public String totalPriceVerify(String totalPrice,HttpSession session){
        String msg = "";
        if(BeanUtils.isDigitFalse(totalPrice) == false){
            msg = "订单总价必须为正整数";
        }else{
            if(totalPrice.length() > 6){
                msg = "订单总价不能超过999999";
            }else{
                msg = "订单总价合法";
            }
        }
        if(msg.equals("订单总价合法")){
            TotalOrders adminUpdateTotalOrder = (TotalOrders) session.getAttribute("adminUpdateTotalOrder");
            if(adminUpdateTotalOrder.getTotalPrice() == Integer.parseInt(totalPrice)){
                msg = "";
            }
        }
        return msg;
    }

    @RequestMapping("/updateTotalOrder")
    public String updateTotalOrder(TotalOrders totalOrders,HttpSession session,Model model){
        if(totalPriceVerify(totalOrders.getTotalPrice() + "", session).equals("") ||
                totalPriceVerify(totalOrders.getTotalPrice() + "", session).equals("订单总价合法")){
            totalOrdersService.updateTotalOrder(totalOrders);
            return "redirect:/totalorder/allTotalOrders";
        }

        model.addAttribute("queriedOrders",totalOrders);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("已付款");
        statusList.add("未付款");
        model.addAttribute("statusList",statusList);
        model.addAttribute("errorMsg","更新失败！请检查输入的数据是否合法");
        return "updateTotalOrder";
    }
}
