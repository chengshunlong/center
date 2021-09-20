package com.zhbit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.Eqps;
import com.zhbit.bean.Sites;
import com.zhbit.service.EqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-09-17:10
 */
@Controller
@RequestMapping("/eqp")
@Transactional
public class EqpController {

    @Autowired
    EqpService eqpService;

/*    @RequestMapping("/allEqps")
    public String allEqps(Model model){
        List<Eqps> eqps = eqpService.queryAllEqp();
        model.addAttribute("queridEqps",eqps);
        return "allEqps";
    }*/

    @RequestMapping("/allEqps")
    public String allEqps(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, Model model){
        //每页10条数据
        PageHelper.startPage(pageNo,10);
        List<Eqps> eqps = eqpService.queryAllEqp();
        //显示5页
        PageInfo<Eqps> eqpsPageInfo = new PageInfo<Eqps>(eqps, 5);
        model.addAttribute("pageInfo",eqpsPageInfo);
        return "allEqps";
    }


    @RequestMapping("/deleteEqp")
    public String deleteEqp(int eid){
//        System.out.println(eqpService.queryEqpByEid(eid));
        eqpService.deleteEqp(eid);
        return "redirect:/eqp/allEqps";
    }

/*    @RequestMapping("/userAllEqps")
    public String userAllEqps(Model model){
        List<Eqps> eqps = eqpService.queryAllEqp();
        model.addAttribute("queiedEqps",eqps);
        return "userEqpLease";
    }*/

    @RequestMapping("/searchEqp")
    public String searchSite(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,String searchParam,Model model){
        PageHelper.startPage(pageNo,10);
        List<Eqps> eqps = eqpService.searchEqp(searchParam);
        PageInfo<Eqps> eqpsPageInfo = new PageInfo<Eqps>(eqps, 5);
        model.addAttribute("pageInfo",eqpsPageInfo);
        return "allEqps";
    }

    @RequestMapping("/userAllEqps")
    public String userAllEqps(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,Model model){
        PageHelper.startPage(pageNo,10);
        List<Eqps> eqps = eqpService.queryAllEqp();
        PageInfo<Eqps> eqpsPageInfo = new PageInfo<Eqps>(eqps, 5);
        model.addAttribute("pageInfo",eqpsPageInfo);
        return "userEqpLease";
    }

//    ================================更新场地======================================
@RequestMapping("/toUpdateEqp")
public String toUpdateEqp(int eid, Model model, HttpSession session){
    Eqps eqps = eqpService.queryEqpByEid(eid);
    session.setAttribute("adminUpdateEqp", eqps);
    model.addAttribute("queriedEqp",eqps);
    return "updateEqp";
}


    @ResponseBody
    @RequestMapping("/adminUpdateEqpNameVerify")
    public String adminUpdateEqpNameVerify(String eqpName,HttpSession session){
        String msg = "";
        if(eqpName.length() > 16 || eqpName.length() < 2){
            msg = "器材名称为2-16位非特殊字符";
        }else{
            if(BeanUtils.isSpecialChar(eqpName) == false){
                msg = "器材名称合法";
            }
            else{
                msg = "器材名称不能包含特殊字符";
            }
        }
        Eqps adminUpdateEqp = (Eqps) session.getAttribute("adminUpdateEqp");
        if(adminUpdateEqp != null){
            System.out.println(adminUpdateEqp);
            if(adminUpdateEqp.getEqpName().equals(eqpName)){
                msg = "";
            }
        }
        return msg;
    }


    @RequestMapping("/adminUpdateEqpPriceVerify")
    @ResponseBody
    public String adminUpdateEqpPriceVerify(String eqpPrice,HttpSession session){
        String msg = "";
        if (BeanUtils.isDigitFalse(eqpPrice) == false){
            msg = "器材价格必须为正整数";
        }else{
            if (eqpPrice.length() > 4) {
                msg = "器材价格最大不能超过9999";
                //数据过大则设为0，防止类型转换报错
//                eqpPrice = "0";
            }else{
                msg = "器材价格合法";
            }
        }
        if(msg == "器材价格合法"){
            Eqps adminUpdateEqp = (Eqps) session.getAttribute("adminUpdateEqp");
            if(adminUpdateEqp != null){
                if(Integer.parseInt(eqpPrice) == adminUpdateEqp.getEqpPrice()){
                    msg = "";
                }
            }
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("/adminUpdateEqpAmountVerify")
    public String adminUpdateEqpAmountVerify(String eqpAmount,HttpSession session){
        String msg = "";
        if (BeanUtils.isDigitFalse(eqpAmount) == false){
            msg = "器材价格必须为正整数";
        }else{
            if (eqpAmount.length() > 4) {
                msg = "器材数量不能大于9999";
            }else{
                msg = "器材数量合法";
            }
        }
        if(msg == "器材数量合法"){
            Eqps adminUpdateEqp = (Eqps) session.getAttribute("adminUpdateEqp");
            if(adminUpdateEqp != null){
                if(Integer.parseInt(eqpAmount) == adminUpdateEqp.getEqpAmount()){
                    msg = "";
                }
            }
        }
        return msg;
    }

    /**
     * 需要在前端限制字段长度
     * @param eqps
     * @param session
     * @return
     */
    @RequestMapping("/updateEqp")
    public String updateEqp(Eqps eqps,HttpSession session,Model model){
        if((adminUpdateEqpNameVerify(eqps.getEqpName(), session).equals("")
                || adminUpdateEqpNameVerify(eqps.getEqpName(), session).equals("器材名称合法"))
            && (adminUpdateEqpPriceVerify(eqps.getEqpPrice() + "", session).equals("")
                || adminUpdateEqpPriceVerify(eqps.getEqpPrice() + "", session).equals("器材价格合法"))
        && (adminUpdateEqpAmountVerify(eqps.getEqpAmount() + "",session).equals("")
                || adminUpdateEqpAmountVerify(eqps.getEqpAmount() + "",session).equals("器材数量合法"))){
//        System.out.println(eqps);
            eqpService.updateEqp(eqps);
//        int i = 10/0;
            session.removeAttribute("adminUpdateEqp");
            return "redirect:/eqp/allEqps";
        }else{
            model.addAttribute("errorMsg","更新失败！请检查输入的数据是否合法");
            model.addAttribute("queriedEqp",eqps);
            return "updateEqp";
        }
    }

//    ==========================新增============================================================================================
    @RequestMapping("/toAddEqp")
    public String toAddEqp(HttpSession session){
        return "addEqp";
    }

    @RequestMapping("/addEqp")
    public String addEqp(Eqps eqps,HttpSession session,Model model){
        if((adminUpdateEqpNameVerify(eqps.getEqpName(), session).equals("")
                || adminUpdateEqpNameVerify(eqps.getEqpName(), session).equals("器材名称合法"))
                && (adminUpdateEqpPriceVerify(eqps.getEqpPrice() + "", session).equals("")
                || adminUpdateEqpPriceVerify(eqps.getEqpPrice() + "", session).equals("器材价格合法"))
                && (adminUpdateEqpAmountVerify(eqps.getEqpAmount() + "",session).equals("")
                || adminUpdateEqpAmountVerify(eqps.getEqpAmount() + "",session).equals("器材数量合法"))) {
            eqpService.addEqp(eqps);
            return "redirect:/eqp/allEqps";
        }
        model.addAttribute("errorEqp",eqps);
        model.addAttribute("errorMsg","新增失败！请检查输入的数据是否合法");
        return "addEqp";
    }



//==============================异常================================================================================

//    @ExceptionHandler(value = DataIntegrityViolationException.class)
//    public ModelAndView dataIntegrityViolationException(Exception exception){
//        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
//        modelAndView.addObject("exceptionInfo",exception);
//        modelAndView.addObject("msg","该器材存在订单，请先处理订单");
//        return modelAndView;
//    }

//    @ExceptionHandler(value = NullPointerException.class)
//    public ModelAndView nullPointException(Exception exception){
//        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
//        modelAndView.addObject("exceptionInfo",exception);
//        modelAndView.addObject("msg","请校验提交的数据是否有效！");
//        return modelAndView;
//    }

}
