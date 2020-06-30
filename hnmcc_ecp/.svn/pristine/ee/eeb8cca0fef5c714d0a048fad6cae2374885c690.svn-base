package com.xwtech.xwecp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.xwtech.xwecp.teletext.BossTeletextUtil;

public class ATest {
	public static ApplicationContext ctx;
	public static Acutomer cu;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "MHF204651860bmxPm3J910016XZT01 HNYD00201608042046510001  1 128 1  0000调用成功                                  FFFFFFFF1\t1\t0";
        System.out.println(a.substring(37,51));
		String b = "";
		if(a.getBytes().length>=51) {
			b = new String(a.getBytes(), 37, 14);
		}
		System.out.println("b:"+b);
	}

}
