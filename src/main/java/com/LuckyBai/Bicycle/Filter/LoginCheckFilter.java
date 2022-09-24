//package com.LuckyBai.Bicycle.Filter;
//
//
//import com.LuckyBai.Bicycle.Common.Result;
//import com.sun.prism.impl.BaseContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.AntPathMatcher;
//import com.alibaba.fastjson.JSON;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
///**
// * 用来检查用户是否已经完成了登录
// * 过滤器
// * urlPatterns = "/*" 作用：用来拦截所有的请求
// */
////@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter {
//    //路径匹配器，支持通配符的写法
//    public  static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
////            1.获取本次请求的url
//        String requestURI = request.getRequestURI();
//        log.info("拦截到请求：{}",requestURI);
//        //定义不需要处理的请求路径
//        String[] urls = new String[]{
//
//                "/user/login",
//                "/user/logout",
//                "/user/register",
//        };
////                * 2.判断本次请求是否系处理
//        boolean check = check(urls,requestURI);
////                * 3.如果不需要处理，就直接放行
//        if(check){
//            log.info("本次请求{}不需要处理",requestURI);
//            filterChain.doFilter(request,response);
//            return;
//        }
////                * 4.判断登录转状态，如果已经登录，就选择直接放行
//        if( request.getSession().getAttribute("user")!=null){
//            log.info("用户已经登陆，用户的id为：{}", request.getSession().getAttribute("user"));
//
//            Long empId = (Long) request.getSession().getAttribute("user");
//
//            BaseContext.setCurrentId(empId);
//            long id = Thread.currentThread().getId();
//            log.info("线程的id为：{}",id);
//
//            filterChain.doFilter(request,response);
//            return;
//        }
////                * 5，如果未登录，就返回未登录的结果,通过输出流的方式向客户端页面响应数据
//        log.info("用户未登录");
//        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
//        return;
//    }
//
//    /**
//     * 进行路径匹配，检查本次请求是否需要放行
//     */
//    public boolean check(String urls[],String requestURL){
//        for (String url : urls) {
//            boolean match = PATH_MATCHER.match(url, requestURL);
//            if(match){
//                return true;
//            }
//        }
//        return false;
//    }
//}
