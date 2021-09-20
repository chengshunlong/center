package com.zhbit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.bean.Orders;
import com.zhbit.bean.Sites;
import com.zhbit.bean.Users;
import com.zhbit.exception.MyDatabaseException;
import com.zhbit.service.OrderService;
import com.zhbit.service.SiteService;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-19:54
 */
@Controller
@RequestMapping("/order")
@Transactional
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    SiteService siteService;
    @Autowired
    UserService userService;

/*    @RequestMapping("/allOrders")
    public String toAllOrders(Model model){
        List<Orders> orders = orderService.queryAllOrder();
        model.addAttribute("queriedOrders",orders);
//        System.out.println(orders);
        return "allOrders";
    }*/

    @RequestMapping("/allOrders")
    public String allOrders(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model){
        PageHelper.startPage(pageNo,10);//每页10条数据
        List<Orders> orders = orderService.queryAllOrder();
        //传入信息 连续显示5页
        PageInfo<Orders> ordersPageInfo = new PageInfo<Orders>(orders, 5);
        model.addAttribute("pageInfo",ordersPageInfo);
        return "allOrders";
    }

    @RequestMapping("/toUpdateOrder")
    public String toUpdateOrder(int oid,Model model){
        Orders orders = orderService.queryOrderByOid(oid);
        model.addAttribute("queriedOrder",orders);
        //用户下拉列表
        List<Users> usersList = userService.queryAllUser();
        model.addAttribute("users",usersList);
        //场地下拉列表
        List<Sites> sitesList = siteService.queryAllSite();
        model.addAttribute("sites",sitesList);
        //订单状态下拉列表
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("已完成");
        statusList.add("未完成");
        model.addAttribute("statusList",statusList);
        return "updateOrder";
    }

    /**
     * 根据用户姓名和场地名称重新查询uid和sid，然后再更新订单信息
     * @param orders
     * @return
     */
    @RequestMapping("/updateOrder")
    public String updateOrder(Orders orders){

        System.out.println(orders);

        Sites sites = siteService.querySiteBySiteName(orders.getSiteName());
        Users users = userService.queryUserByUserName(orders.getUserName());
        int sid = sites.getSid();
        int uid = users.getUid();
        orders.setSid(sid);
        orders.setUid(uid);

        orderService.updateOrder(orders);
        return "redirect:/order/allOrders";
    }

    @RequestMapping("/toAddOrder")
    public String toAddOrder(Model model){
        //用户名下拉列表
        List<Users> usersList = userService.queryAllUser();
        model.addAttribute("users",usersList);
        //场地下拉列表
        List<Sites> sitesList = siteService.queryAllSite();
        model.addAttribute("sites",sitesList);
        //订单状态下拉列表
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("已完成");
        statusList.add("未完成");
        model.addAttribute("status",statusList);
        return "addOrder";
    }

    @RequestMapping("/addOrder")
    public String addOrder(Orders orders){
        Sites sites = siteService.querySiteBySiteName(orders.getSiteName());
        Users users = userService.queryUserByUserName(orders.getUserName());
        int sid = sites.getSid();
        int uid = users.getUid();
        orders.setSid(sid);
        orders.setUid(uid);
//        System.out.println(orders);
        orderService.addOrder(orders);
        return "redirect:/order/allOrders";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(int oid){
//        System.out.println(orderService.queryOrderByOid(oid));
        orderService.deleteOrder(oid);
        return "redirect:/order/allOrders";
    }

    /**
     * 租赁
     * @return
     */
    @RequestMapping("/leaseSite")
    public String leaseSite(int sid, HttpSession session) throws MyDatabaseException {
        Sites site = siteService.querySiteBySid(sid);
        //场地状态为“可用”才可以预定
        if(site.getSiteStatus().equals("可用")){
            Users users = (Users) session.getAttribute("user");
            //根据userId获取到Users对象
            Users user = userService.queryUserByUserId(users.getUserId());
            Orders orders = new Orders(0,user.getUid(),site.getSid(),user.getUserName(),site.getSiteName(),"未完成");
            orderService.createOrder(orders);

//            int a = 10/0;

            //创建订单以后将场地状态改为“不可用”
            siteService.updateSite(new Sites(site.getSid(),site.getSiteName(),site.getSitePicture(),"不可用",site.getTid()));
            return "redirect:/order/toUserAllOrders";
        }
        throw new MyDatabaseException("该场地不可用！");
    }

    /**
     * 查询当前用户的场地订单信息
     * @param session
     * @return
     */
/*    @RequestMapping("/toUserAllOrders")
    public String toUserAllOrders(Model model,HttpSession session){
        Users users = (Users) session.getAttribute("user");
        //session中的users只有userId和userPwd属性,需要查出Uid
        Users user = userService.queryUserByUserId(users.getUserId());
        //查询当前用户的场地订单信息
        List<Orders> orders = orderService.queryOrderByUid(user.getUid());
//        System.out.println(orders);
        model.addAttribute("orders",orders);
        return "userAllOrders";
    }*/

    @RequestMapping("/toUserAllOrders")
    public String toUserAllOrders(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model,HttpSession session){
        Users users = (Users) session.getAttribute("user");
        //session中的users只有userId和userPwd属性,需要查出Uid
        Users user = userService.queryUserByUserId(users.getUserId());
        PageHelper.startPage(pageNo,10);
        //查询当前用户的场地订单信息
        List<Orders> orders = orderService.queryOrderByUid(user.getUid());
        PageInfo<Orders> ordersPageInfo = new PageInfo<Orders>(orders, 5);
        model.addAttribute("pageInfo",ordersPageInfo);
        return "userAllOrders";
    }

    @RequestMapping("/siteApprove")
    public String siteApprove(int oid){
        orderService.approveSite(oid);
        return "redirect:/order/allOrders";
    }

    @RequestMapping("/searchOrder")
    public ModelAndView searchOrder(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,String searchParam){
        ModelAndView modelAndView = new ModelAndView("allOrders");
        PageHelper.startPage(pageNo,10);
        List<Orders> orders = orderService.searchOrder(searchParam);
        PageInfo<Orders> ordersPageInfo = new PageInfo<Orders>(orders, 5);
        modelAndView.addObject("pageInfo",ordersPageInfo);
        return modelAndView;
    }

    @ExceptionHandler(MyDatabaseException.class)
    public ModelAndView myDatabaseException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("exceptionInfo",exception);
        modelAndView.addObject("msg","该场地不可用！");
        return modelAndView;
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView nullPointException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("exceptionInfo",exception);
        modelAndView.addObject("msg","请校验提交的数据是否有效！");
        return modelAndView;
    }
}
