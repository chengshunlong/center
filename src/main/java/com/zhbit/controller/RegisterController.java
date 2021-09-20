package com.zhbit.controller;

import com.zhbit.Utils.BeanUtils;
import com.zhbit.bean.Users;
import com.zhbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-12-14:02
 */
@Controller
@RequestMapping("/regist")
@Transactional
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/toRegister")
    public ModelAndView toRegister(){
        ModelAndView modelAndView = new ModelAndView("register");
        ArrayList<String> types = new ArrayList<String>();
//        List<String> types = userService.queryAllTypes();
//        types.remove("admin");
        types.add("teacher");
        types.add("student");
        modelAndView.addObject("types",types);

        return modelAndView;
    }

    @RequestMapping("/registUserIdVerify")
    @ResponseBody
    public String registUserIdVerify(String userId){
        String msg = "";
        if(userId.length() > 16 || userId.length() < 6){
            msg = "账号长度必须为6 - 16位";
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
                    msg = "账号必须为数字、英文或数字英文组合";
                }
            }
        }
        return msg;
    }

    @RequestMapping("/registUserPwdVerify")
    @ResponseBody
    public String registUserPwdVerify(String userPwd){
        String msg = "";
        if(userPwd.length() > 20 || userPwd.length() < 6){
            msg = "密码长度必须为6 - 20位";
        }else{
            msg = "密码合法";
        }
        return msg;
    }

    @RequestMapping("/registUserNameVerify")
    @ResponseBody
    public String registUserNameVerify(String userName){
        String msg = "";
        for (int i = 0; i < userName.length(); i++) {
            boolean isChinese = BeanUtils.isChinese(userName.charAt(i));
            if(isChinese == false){
                msg = "姓名必须为中文字符";
            }else{
                if(userName.length() < 2){
                    msg = "姓名为2-10位中文字符";
                }else{
                    msg = "姓名合法";
                }
            }
        }
        return msg;
    }


    @RequestMapping("/registUserTelVerify")
    @ResponseBody
    public String registUserTelVerify(String userTel){
        String msg = "";
        if(BeanUtils.isChinaPhoneLegal(userTel) == false){
            msg = "请输入合法的手机号码";
        }else{
            msg = "手机号码合法";
        }
        return msg;
    }




    @RequestMapping("/register")
    public String register(Users users, Model model){
        String msg = "";
        //查询数据库中的userId 如果不存在，继续注册
        if(userService.queryUserByUserId(users.getUserId()) == null && registUserNameVerify(users.getUserName()).equals("姓名合法") && registUserPwdVerify(users.getUserPwd()).equals("密码合法")
        && registUserTelVerify(users.getUserTel()).equals("手机号码合法")){
            System.out.println(userService.queryUserByUserId(users.getUserId()));
            userService.addUser(users);
            msg = "成功";
            return "redirect:/login/toLogin";
        } else {
            msg = "请检查输入字段是否合法";
            model.addAttribute("msg",msg);
//            List<String> types = userService.queryAllTypes();
//            model.addAttribute("types",types);
            ArrayList<String> types = new ArrayList<String>();
//        List<String> types = userService.queryAllTypes();
//        types.remove("admin");
            types.add("teacher");
            types.add("student");
            model.addAttribute("types",types);
            return "register";
        }
    }


}
