package com.example.encore_spring_pjt.ctrl.user.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 인터셉터의 작동원리 및 순서
           xxxInterceptor(preHandler, postHandler)
 client -------------------> preHandler 에서 요청을 가로채면 Controller 수행 X
 client -------------------> Controller 수행하고 ---------postHandler---------> View(or redirect)
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("debug >>>> LoginInterceptor preHandler >>>>>>>>>>");
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") != null) {
            System.out.println("debug >>> session exists (Y)");
            response.sendRedirect("/board/list.hanwha");
        }
        return true;
    }
    /*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("debug >>>> LoginInterceptor postHandler >>>>>>>>>>");
        System.out.println("debug >>>> LoginInterceptor END >>>>>>>>>>");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    */
}
