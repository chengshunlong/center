package com.zhbit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.Eqps;
import com.zhbit.bean.Orders;
import com.zhbit.bean.TotalOrders;
import com.zhbit.bean.Users;
import com.zhbit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.main.client.MozillaServiceDelegate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    OrderService orderService;
    @Autowired
    TotalOrdersService totalOrdersService;

//    @Autowired
//    EOrderService eOrderService;


    /**
     * 查看所有用户信息
     * @param model
     * @return
     */
/*    @RequestMapping("/allUsers")
    public String list(Model model){
        List<Users> users = userService.queryAllUser();
        model.addAttribute("users",users);
        return  "allUsers";
    }*/

    @RequestMapping("/allUsers")
    public String list(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, Model model){
        PageHelper.startPage(pageNo,10);
        List<Users> users = userService.queryAllUser();
        PageInfo<Users> usersPageInfo = new PageInfo<Users>(users, 5);
        model.addAttribute("pageInfo",usersPageInfo);
        return  "allUsers";
    }



    @RequestMapping("/deleteUser")
    public String deleteUser(int uid){
        System.out.println(uid);
        userService.deleteUser(uid);
        return "redirect:/user/allUsers";
    }

    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam(value = "pageNo",defaultValue = "1")Integer pageNo,String searchParam,Model model){
        PageHelper.startPage(pageNo,10);
        List<Users> usersList = userService.searchUser(searchParam);
        PageInfo<Users> usersPageInfo = new PageInfo<Users>(usersList, 5);
        model.addAttribute("pageInfo",usersPageInfo);
        return "allUsers";
    }



//  =====================================================================================================================

    @RequestMapping("/toUpdateBySelf")
    public String toUpdateBySelf(){
        return "userUpdate";
    }


    /**
     * 普通用户修改信息的表单验证
     * @param userPwd
     * @return
     */
    @RequestMapping("/updateUserPwdVerify")
    @ResponseBody
    public String updateUserPwdVerify(String userPwd){
        String msg = "";
        if(userPwd.length() > 20 || userPwd.length() < 6){
            msg = "密码长度为6-20位";
        } else {
            msg = "密码合法";
        }
        if(userPwd.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 普通用户修改信息的表单验证
     * @param userName
     * @return
     */
    @RequestMapping("/updateUserNameVerify")
    @ResponseBody
    public String updateUserNameVerify(String userName){
        String msg = "";
        for (int i = 0; i < userName.length(); i++) {
            System.out.println(userName.length());
            boolean isChinese = BeanUtils.isChinese(userName.charAt(i));
            if(isChinese == false){
                msg = "姓名必须为中文字符";
                break;
            }else{
                if(userName.length() < 2 || userName.length() > 10){
                    msg = "姓名为2-10位中文字符";
                    break;
                }else{
                    msg = "姓名合法";
                }
            }
        }
        if(userName.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 普通用户修改信息的表单验证
     * @param userTel
     * @return
     */
    @RequestMapping("/updateUserTelVerify")
    @ResponseBody
    public String updateUserTelVerify(String userTel){
        String msg = "";
        if(BeanUtils.isChinaPhoneLegal(userTel) == false){
            msg = "请输入正确的手机号码";
        }else{
            msg = "电话号码合法";
        }
        if(userTel.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 普通用户修改信息的表单验证
     * @param user
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/updateBySelf")
    public String updateBySelf(Users user,HttpSession session,Model model){
        String msg = "";
        if(updateUserPwdVerify(user.getUserPwd()).equals("密码合法") && updateUserNameVerify(user.getUserName()).equals("姓名合法")
        && updateUserTelVerify(user.getUserTel()).equals("电话号码合法")){
            Users users = (Users) session.getAttribute("user");
            int uid = users.getUid();
            String type = users.getUserType();
            //sql语句没写动态查询，需要先获取user的uid和type
            user.setUid(uid);
            user.setUserType(type);
            session.setAttribute("user", user);
            userService.updateUser(user);
            return "userIndex";
        }else{
            msg = "请检查输入字段是否合法";
            model.addAttribute("msg",msg);
            return "userUpdate";
        }
    }

//    ===========================================================================================================

    /**
     * 跳转到更新用户信息页面
     * @return
     */
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(int uid,Model model,HttpSession session){
        Users users = userService.queryUserByUid(uid);
        session.setAttribute("adminUpdateUser",users);
        List<String> userTypes = new ArrayList<String>();
        userTypes.add("student");
        userTypes.add("teacher");
        userTypes.add("admin");
        model.addAttribute("userTypes",userTypes);
        model.addAttribute("queriedUser",users);
        return "updateUser";
    }

    /**
     * 管理员更新用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/adminUpdateUserIdVerify")
    @ResponseBody
    public String adminUpdateUserIdVerify(String userId,HttpSession session){
        String msg = "";
        if(userId.length() > 16 || userId.length() < 6){
            msg = "账号必须为6 - 16位数字、英文或数字英文组合";
        }else{
            for (int i = 0; i < userId.length(); i++) {
                if(Character.isDigit(userId.charAt(i)) || Character.isLowerCase(userId.charAt(i)) || Character.isUpperCase(userId.charAt(i))){
                    //根据userId查，存在则回显用户名已存在
                    if(userService.queryUserByUserId(userId) != null){
                        Users user = (Users) session.getAttribute("adminUpdateUser");
                        if(user.getUserId().equals(userId)){
                            msg = "";
                        }else{
                            msg = "账号已存在";
                        }
                    } else {
                        msg = "账号合法";
                    }
                }else{
                    msg = "账号必须为6 - 16位数字、英文或数字英文组合";
                    break;
                }
            }
        }
        if(userId.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 管理员更新用户信息
     * @param userPwd
     * @return
     */
    @RequestMapping("/adminUpdateUserPwdVerify")
    @ResponseBody
    public String adminUpdateUserPwdVerify(String userPwd,HttpSession session){
        String msg = "";
        if(userPwd.length() > 20 || userPwd.length() < 6){
            msg = "密码长度必须为6 - 20位";
        }else{
            Users user = (Users) session.getAttribute("adminUpdateUser");
            if(user.getUserPwd().equals(userPwd)){
                msg = "";
            }else{
                msg = "密码合法";
            }
        }
        if(userPwd.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 管理员更新用户信息
     * @param userName
     * @return
     */
    @RequestMapping("/adminUpdateUserNameVerify")
    @ResponseBody
    public String adminUpdateUserNameVerify(String userName,HttpSession session){
        String msg = "";
        for (int i = 0; i < userName.length(); i++) {
            boolean isChinese = BeanUtils.isChinese(userName.charAt(i));
            if(isChinese == false){
                msg = "姓名必须为中文字符";
                break;
            }else{
                if(userName.length() < 2){
                    msg = "姓名为2-10位中文字符";
                }else{
                    Users user = (Users) session.getAttribute("adminUpdateUser");
                    if(user.getUserName().equals(userName)){
                        msg = "";
                    }else{
                        msg = "姓名合法";
                    }
                }
            }
        }
        if(userName.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 管理员更新用户信息
     * @param userTel
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminUpdateUserTelVerify")
    public String adminUpdateUserTelVerify(String userTel,HttpSession session){
        String msg = "";
        if(BeanUtils.isChinaPhoneLegal(userTel) == false){
            msg = "请输入合法的手机号码";
        }else{
            Users user = (Users) session.getAttribute("adminUpdateUser");
            if(user.getUserTel().equals(userTel)){
                msg = "";
            }else{
                msg = "手机号码合法";
            }
        }
        if(userTel.length() == 0){
            msg = "";
        }
        return msg;
    }

    /**
     * 更新用户信息
     * 同时修改场地和器材订单
     * @param users
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(Users users,HttpSession session,Model model){
        if((adminUpdateUserIdVerify(users.getUserId(),session).equals("") || adminUpdateUserIdVerify(users.getUserId(), session).equals("账号合法"))
        &&(adminUpdateUserPwdVerify(users.getUserPwd(),session).equals("") || adminUpdateUserPwdVerify(users.getUserPwd(),session).equals("密码合法"))
        &&(adminUpdateUserNameVerify(users.getUserName(),session).equals("") || adminUpdateUserNameVerify(users.getUserName(),session).equals("姓名合法"))
        &&(adminUpdateUserTelVerify(users.getUserTel(),session).equals("") || adminUpdateUserTelVerify(users.getUserTel(),session).equals("手机号码合法"))){
            userService.updateUser(users);
            int uid = users.getUid();
            //查出包含此用户信息的场地订单和总订单，一同修改
            List<Orders> orders = orderService.queryOrderByUid(uid);
            for (Orders order : orders) {
                order.setUserName(users.getUserName());
                orderService.updateOrder(order);
            }
            List<TotalOrders> totalOrders = totalOrdersService.queryAllTotalOrdersByUid(uid);
            for (TotalOrders totalOrder : totalOrders) {
                totalOrder.setUserName(users.getUserName());
                totalOrder.setUserId(users.getUserId());
                totalOrder.setUserTel(users.getUserTel());
                totalOrdersService.updateTotalOrder(totalOrder);
            }
            session.removeAttribute("adminUpdateUser");
            return "redirect:/user/allUsers";
        }
        List<String> userTypes = new ArrayList<String>();
        userTypes.add("student");
        userTypes.add("teacher");
        userTypes.add("admin");
        model.addAttribute("userTypes",userTypes);
        model.addAttribute("queriedUser",users);
        model.addAttribute("errorMsg","修改失败！请检查输入的数据是否合法");
        return "updateUser";
    }
//    ========================管理员添加用户==============================================================================================

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping("/toAddUser")
    public String toAddPage(Model model){
        List<String> userTypes = new ArrayList<String>();
        userTypes.add("student");
        userTypes.add("teacher");
        userTypes.add("admin");
        model.addAttribute("userTypes",userTypes);
        return "addUser";
    }

    @RequestMapping("/adminAddUserIdVerify")
    @ResponseBody
    public String adminAddUserIdVerify(String userId){
        String msg = "";
        if(userId.length() > 16 || userId.length() < 6){
            msg = "账号必须为6 - 16位数字、英文或数字英文组合";
        }else{
            for (int i = 0; i < userId.length(); i++) {
                if(Character.isDigit(userId.charAt(i)) || Character.isLowerCase(userId.charAt(i)) || Character.isUpperCase(userId.charAt(i))){
                    //根据userId查，存在则回显用户名已存在
                    if(userService.queryUserByUserId(userId) != null){
                        msg = "账号已存在";
                    } else {
                        msg = "账号合法";
                    }
                }else{
                    msg = "账号必须为6 - 16位数字、英文或数字英文组合";
                    break;
                }
            }
        }
        if(userId.length() == 0){
            msg = "";
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("/adminAddUserPwdVerify")
    public String adminAddUserPwdVerify(String userPwd){
        String msg = "";
        if(userPwd.length() > 20 || userPwd.length() < 6){
            msg = "密码长度必须为6 - 20位";
        }else{
            msg = "密码合法";
        }
        if(userPwd.length() == 0){
            msg = "";
        }
        return msg;
    }


    @RequestMapping("/adminAddUserNameVerify")
    @ResponseBody
    public String adminAddUserNameVerify(String userName){
        String msg = "";
        for (int i = 0; i < userName.length(); i++) {
            boolean isChinese = BeanUtils.isChinese(userName.charAt(i));
            if(isChinese == false){
                msg = "姓名必须为中文字符";
                break;
            }else{
                if(userName.length() < 2){
                    msg = "姓名为2-10位中文字符";
                    break;
                }else{
                    msg = "姓名合法";
                }
            }
        }
        if(userName.length() == 0){
            msg = "";
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("/adminAddUserTelVerify")
    public String adminAddUserTelVerify(String userTel){
        String msg = "";
        if(BeanUtils.isChinaPhoneLegal(userTel) == false){
            msg = "请输入合法的手机号码";
        }else{
            msg = "手机号码合法";
        }
        if(userTel.length() == 0){
            msg = "";
        }
        return msg;
    }


    /**
     * 添加新用户
     * @param users
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(Users users,Model model){
        if(adminAddUserIdVerify(users.getUserId()).equals("账号合法") && adminAddUserPwdVerify(users.getUserPwd()).equals("密码合法")
        && adminAddUserNameVerify(users.getUserName()).equals("姓名合法") && adminAddUserTelVerify(users.getUserTel()).equals("手机号码合法")){
            userService.addUser(users);
            return "redirect:/user/allUsers";
        }else{
            model.addAttribute("errorAddUser",users);
            List<String> userTypes = new ArrayList<String>();
            userTypes.add("student");
            userTypes.add("teacher");
            userTypes.add("admin");
            model.addAttribute("userTypes",userTypes);
            model.addAttribute("errorMsg","添加失败！请检查输入数据是否合法");
            return "addUser";
        }
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView dataIntegrityViolationException(Exception exception){
        ModelAndView modelAndView = new ModelAndView("exceptionHandle");
        modelAndView.addObject("msg","数据库中存在当前用户的订单，请先删除订单!");
        modelAndView.addObject("exceptionInfo",exception);
        return modelAndView;
    }

}
