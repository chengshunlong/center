package com.zhbit.controller;

import com.github.pagehelper.PageHelper;
import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.*;
import com.zhbit.exception.MyDatabaseException;
import com.zhbit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-11-13:18
 */
@Controller
@RequestMapping("/shoppingcart")
@Transactional
public class ShoppingCartController {

    @Autowired
    EqpService eqpService;
    @Autowired
    EOrderService eOrderService;
    @Autowired
    UserService userService;
    @Autowired
    TotalOrdersService totalOrdersService;

    @RequestMapping("/toShoppingCart")
    public String toShoppingCart() {
        return "shoppingCart";
    }

    /**
     * 添加商品到购物车
     *
     * @param eid
     * @param session
     * @return
     */
    @RequestMapping("/addCartItem")
    public String addCartItem(int eid, HttpSession session) {
        Eqps eqps = eqpService.queryEqpByEid(eid);

        CartItem cartItem = new CartItem(eqps.getEid(), eqps.getEqpName(), eqps.getEqpPrice(), 1, eqps.getEqpPicture(), eqps.getEqpPrice());
        //获取到购物车
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

        //不存在就新建并存放到session中
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        //如果数据库中eqps的数量不为0，且购物车内没有此商品
        if (eqps.getEqpAmount() > 0) {
            shoppingCart.addItem(cartItem);
            return "redirect:/eqp/userAllEqps";
        }
        return "redirect:/eqp/userAllEqps";
    }

    /**
     * 清空购物车
     *
     * @param session
     * @return
     */
    @RequestMapping("/clearShoppingCart")
    public String clearShoppingCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        //添加了c:if则不需要判断
        if (shoppingCart != null) {
            shoppingCart.clear();
            return "redirect:/shoppingcart/toShoppingCart";
        }
        return "redirect:/shoppingcart/toShoppingCart";
    }

    /**
     * 更新购物车数量
     *
     * @param eid
     * @param amount
     * @param session
     * @return
     */
    @RequestMapping("/updateAmount")
    public String updateAmount(int eid, int amount, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

        if (shoppingCart != null) {
            shoppingCart.updateAmount(eid, amount);
            return "redirect:/shoppingcart/toShoppingCart";
        }
        return "redirect:/shoppingcart/toShoppingCart";
    }


    @RequestMapping("/deleteItem")
    public String deleteItem(int eid, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            shoppingCart.deleteItem(eid);
            return "redirect:/shoppingcart/toShoppingCart";

        }
        return "redirect:/shoppingcart/toShoppingCart";
    }


    /**
     * 点击付款，创建TotalOrder和对应的包含的EOrder订单
     *
     * 因为器材订单详情表依赖总订单表的toid
     * 所以应先创建totalOrders订单（需要提前计算出总价totalPrice）
     *
     * @return
     */
    @RequestMapping("/payment")
    public String leaseEqps(HttpSession session) throws MyDatabaseException {
        //session中获取购物车和当前用户
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Users user = (Users) session.getAttribute("user");

        //获取EOrder订单项
        Map<Integer, CartItem> items = shoppingCart.getItems();

        //创建总订单,totalPrice先设为空
        //toid使用UUID创建，并保存toid值
        String toid = BeanUtils.getUUID();
        TotalOrders totalOrder = new TotalOrders(toid, user.getUid(), user.getUserId(), user.getUserName(), user.getUserTel(), 0, "已付款","");
        totalOrdersService.addTotalOrder(totalOrder);
        //遍历购物车
        int sum = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            CartItem item = entry.getValue();
            Eqps sqlEqp = eqpService.queryEqpByEid(item.getEid());
//            判断数量是否大于数据库中库存数量
            if (item.getEqpAmount() > sqlEqp.getEqpAmount()) {
                throw new MyDatabaseException();
            } else {
                //更新数据库中eqps表的数据
                //用于更新数量 只需要eid和eqpAmount属性
                eqpService.updatePaymentEqp(new Eqps(item.getEid(), "", 0, item.getEqpAmount(), ""));
                //在数据库中创建订单
                EOrders eOrders = new EOrders(0, item.getEid(), toid, item.getEqpName(), item.getEqpPrice(), item.getEqpAmount(), item.getTotalPrice());
                eOrderService.addEOrder(eOrders);
                //更新数据库中总订单的总价
                totalOrder.setTotalPrice(totalOrder.getTotalPrice() + item.getTotalPrice());
            }
        }

        totalOrdersService.updateTotalOrder(totalOrder);
        shoppingCart.clear();
        return "redirect:/totalorder/toUserAllTotalOrders";
    }

    @ExceptionHandler(MyDatabaseException.class)
    public ModelAndView myDatabaseException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("exceptionInfo",exception);
        modelAndView.addObject("msg","您购买的数量不能大于库存");
        return modelAndView;
    }

}
