//package com.LuckyBai.Bicycle.Common;
//
///**
// *基于ThreadLocal封装的工具类，用于保存和获取当前用户的id
// *
// */
//public class BaseContext {
//    private static ThreadLocal<Long>  threadLocal = new ThreadLocal<>();
//
//    /**
//     * 设置数值
//     * @param id
//     */
//    public  static void setCurrentId(Long id){
//        threadLocal.set(id);
//    }
//
//    /**
//     * 获取数值
//     * @return
//     */
//
//     public static Long getCurrentId(){
//
//        return threadLocal.get();
//     }
//
//}
