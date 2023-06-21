package com.Utils;

import com.Pojo.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        String url=request.getContextPath();
        String uri=request.getRequestURI();

        System.out.println("dddd"+url);
        System.out.println("dsa"+uri);
        if(userInfo!=null){
            //判断是否进的是查询方法
           /* if(servletPath.equals("/menu_selectMenu")){

            }*/
            return true;
        }
//        if(url.indexOf("login")>=0){
//            return true;
//        }
        else{
         request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
