package com.xwtech.xwecp.util;

import java.util.Random;

public class RandomStr {
	//生成随机字符时，字符来源  
    private static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
    private static int str_len = str.length();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		   for(int a = 0;a<5;a++){  
//	            System.out.println(getRandomStr(15));  
//	        }  
//	        System.out.println("-----------------");  
	        for(int a = 0;a<60;a++){  
	            System.out.println(getRandomNum(2));  
	        }
	}
    /** 
     * 获取指定位数的随机数(各种类型字符混合) 
     * @param length 
     * @return 
     */  
    public static String getRandomStr(int length) {  
        if (length <= 0) {  
            length = 1;  
        }  
        Random random = new Random();  
        StringBuilder res = new StringBuilder();  
        for (int i = 0; i < length; i++) {  
            res.append(str.charAt(random.nextInt(str_len)));  
        }  
        return res.toString();  
    }    
       
    /** 
     * 获取指定位数的随机数(纯数字) 
     * @param length 随机数的位数 
     * @return String 
     */  
    public static String getRandomNum(int length) {  
        if (length <= 0) {  
            length = 1;  
        }  
        StringBuilder res = new StringBuilder();  
        Random random = new Random();  
        int i = 0;  
        while (i < length) {  
            res.append(random.nextInt(10));  
            i++;  
        }  
        return res.toString();  
    } 
}
