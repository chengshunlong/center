package com.zhbit.Interceptor;

import com.zhbit.bean.Users;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author livestrong
 * @Date 2021-05-10-23:05
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        //包含Login和login的放行（toLogin、login）
        if(request.getRequestURI().contains("Login") || request.getRequestURI().contains("login")){
            return true;
        }
        if(request.getRequestURI().contains("Register") || request.getRequestURI().contains("register") || request.getRequestURI().contains("regist") || request.getRequestURI().contains("Regist")){
            return true;
        }
        if(request.getRequestURI().contains("static")){
            return true;
        }


        //登录了，放行
        //有session放行
        if(session.getAttribute("user") != null){
            Users user = (Users) session.getAttribute("user");
            //是管理员，放行全部
            if(user.getUserType().equals("admin")){
                return true;
            } else {
                //普通用户
                if(request.getRequestURI().contains("toUserIndex") || request.getRequestURI().contains("userAllSites") || request.getRequestURI().contains("leaseSite")
                        || request.getRequestURI().contains("userAll") || request.getRequestURI().contains("shopping") || request.getRequestURI().contains("toUserAllEOrders")
                        || request.getRequestURI().contains("static") || request.getRequestURI().contains("Self")
                        || request.getRequestURI().contains("User")){
                    return true;
                } else {

                    request.setAttribute("msg", "你无权限访问该URI！");
                    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
                    return false;
                }
            }
        }

        /*Update Admin Add*/
        /*allUsers*/
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }
}
