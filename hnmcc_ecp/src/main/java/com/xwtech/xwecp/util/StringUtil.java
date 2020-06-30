package com.xwtech.xwecp.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static final String arrTest[] ={"[br]","[/b]","[/i]","[/u]","[/size]","[/color]","[/align]","[/url]","[/email]","[/img]"};
	public static final String arrParam[]={"\\[br\\]","\\[b\\](.+?)\\[/b\\]",
						"\\[i\\](.+?)\\[/i\\]",
						"\\[u\\](.+?)\\[/u\\]",
						"\\[size=(.+?)\\](.+?)\\[/size\\]",
						"\\[color=(.+?)\\](.+?)\\[/color\\]",
						"\\[align=(.+?)\\](.+?)\\[/align\\]",
						"\\[url=(.+?)\\](.+?)\\[/url\\]",
						"\\[email=(.+?)\\](.+?)\\[/email\\]," +
						"\\[img=(.+?)\\](.+?)\\[/img\\]"};
	public static final String arrCode[]={"<br>","<b>$1</b>","<i>$1</i>","<u>$1</u>",
						"<font size=\"$1\">$2</font>",
						"<font color=\"$1\">$2</font>",
						"<div align=\"$1\">$2</div>",
						"<a href=\"$1\" target=\"_blank\">$2</a>",
						"<a href=\"email:$1\">$2</a>",
						"<img src=\"$1\" border=0>$2</img>"};
	
	
	
	public static int getInt(String content) {
		int intContent;
    	try{
    		intContent = Integer.parseInt(content);
    	}catch(Exception e){
    		intContent = 0;
    	}
    	return intContent;
	}

	public static long getLong(String content) {
		long lngContent;
    	try{
    		lngContent = Long.parseLong(content);
    	}catch(Exception e){
    		lngContent = 0L;
    	}
    	return lngContent;
	}
	
	/**
	 * 将指定的对象转换为String类型
	 * 
	 * @param curObject
	 *            传入对象参数
	 * @return String
	 */
	public static String getString(Object curObject) {
		if (null == curObject) {
			throw new NullPointerException("The input object is null.");
		} else {
			return curObject.toString();
		}
	}
	
    /**
     * 转换字符,用于替换提交的数据中存在非法数据:"'"
     * @param content
     * @return
     */
    public static String replaceChar(String content) {
        String newstr = "";
        newstr = content.replaceAll("\'", "''");
        return newstr;
    }

    /**
     * 对标题""转换为中文“”采用对应转换
     * @param content
     * @return
     */
    public static  String replaceSymbol(String content){
        int intPlaceNum=0;
        int Num=0;
        String strContent =content;
        while(true){
            //判断是否还存在"
            intPlaceNum=strContent.indexOf("\"");
            if(intPlaceNum<0){
                break;
            }else{
              if(Num%2==0){strContent=strContent.replaceFirst("\"","“");
              }else{strContent=strContent.replaceFirst("\"","”");}
              Num=Num+1;
            }
        }
        return strContent;
    }

    /**
     * 替换HTML标记
     * @param content
     * @return
     */
    public static  String replaceCharToHtml(String content){
         String strContent =content;
         strContent = strContent.replaceAll("<", "&lt;");
         strContent = strContent.replaceAll(">", "&gt;");
         strContent = strContent.replaceAll("\"", "&quot;");
         return strContent;
    }

    public static  String replaceHtmlToChar(String content){
        String strContent =content;
        strContent = strContent.replaceAll("&lt;", "<");
        strContent = strContent.replaceAll("&gt;", ">");
        strContent = strContent.replaceAll("&quot;", "\"");
        return strContent;
   }

    //数据库替换
    public static String replaceCharToSql (String content){
        String strContent =content;
        strContent = strContent.replaceAll("%", "\\\\%");
        return strContent;
   }

    public static String toHtmlValue(String value)
    {
        if (null == value)
        {
            return null;
        }
        char a = 0;
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < value.length(); i++)
        {
            a = value.charAt(i);
            switch (a)
            {
            // 双引号
            case 34:
                buf.append("&#034;");
                break;
                // &号
            case 38:
                buf.append("&amp;");
                break;
                // 单引号
            case 39:
                buf.append("&#039;");
                break;
                // 小于号
            case 60:
                buf.append("&lt;");
                break;
                // 大于号
            case 62:
                buf.append("&gt;");
                break;
            default:
                buf.append(a);
                break;
            }
        }
        return buf.toString();
    }
    
    
    
    
    /**
     * 标题中含有特殊字符替换 如:●▲@◎※ 主要在标题中使用
     * @param content
     * @return
     */
    public static  String replaceSign(String content){
          String strContent="";
          strContent = content.replaceAll("\\*", "");
          strContent = strContent.replaceAll("\\$", "");
          strContent = strContent.replaceAll("\\+", "");
          String arrStr[]={":", "：", "●", "▲", "■", "@", "＠",
        		  "◎", "★", "※", "＃", "〓", "＼", "§", "☆",
        		  "○", "◇", "◆", "□", "△", "＆", "＾", "￣",
        		  "＿","♂","♀","Ю","┭","①","「","」","≮","§",
        		  "￡","∑","『","』","⊙","∷","Θ","の","↓","↑",
        		  "Ф","~","Ⅱ","∈","┣","┫","╋","┇","┋","→",
        		  "←","!","Ж","#"};
          for (int i = 0; i<arrStr.length; i++) {
               if((strContent.indexOf(arrStr[i]))>=0){
                   strContent = strContent.replaceAll(arrStr[i], "");
               }
           }

       return strContent;
     }

    /**
     * 替换所有英文字母
     * @param content
     * @return
     */
    public static  String replaceLetter(String content){
        String strMark="[^[A-Za-z]+$]";
        String strContent="";
        strContent = content.replaceAll(strMark, "");
        return strContent;
   }

   /**
    * 替换所有数字
    * @param content
    * @return
    */
   public static  String replaceNumber(String content){
       String strMark="[^[0-9]+$]";
       String strContent="";
       strContent = content.replaceAll(strMark, "");
       return strContent;
  }

  /**
   * 将/n转换成为回车<br> ,空格转为&nbsp;
   * @param content
   * @return
   */
   public static  String replaceBr(String content){
	   if(content==null){return "";}
	   String strContent = "";

	  // String strMark ="[/\n\r\t]";

	   //strContent = content.replaceAll(strMark,"<br>");

	   strContent = content.replaceAll("\n\r\t", "<br>");
	   strContent = strContent.replaceAll("\n\r", "<br>");
	   strContent = strContent.replaceAll("\r\n", "<br>");
	   strContent = strContent.replaceAll("\n", "<br>");
	   strContent = strContent.replaceAll("\r", "<br>");
	   strContent = strContent.replaceAll(" ", "&nbsp;");
       return strContent;
   }

   /**
    * 清除所有<>标记符号 主要在搜索中显示文字内容 而不显示样式
    * @param content
    * @return
    */
   public static String replaceMark(String content) {
      String strContent = "";
      String strMark="<\\s*[^>]*>";
      strContent=content.trim();
      strContent = strContent.replaceAll("\"", "");
      strContent = strContent.replaceAll("\'", "");
      //删除所有<>标记
      strContent = strContent.replaceAll(strMark, "");
      strContent = strContent.replaceAll("&nbsp;", "");
      strContent = strContent.replaceAll(" ", "");
      strContent = strContent.replaceAll("　", "");
      strContent = strContent.replaceAll("\r", "");
      strContent = strContent.replaceAll("\n", "");
      strContent = strContent.replaceAll("\r\n", "");
      return strContent;
   }

   /**
    * 清楚WOrd垃圾代码
    * @param content
    * @return
    */
   public static String clearWord(String content) {
      String strContent = "";
      strContent=content.trim();
      strContent = strContent.replaceAll("x:str", "");
      //Remove Style attributes
      strContent = strContent.replaceAll("<(\\w[^>]*) style=\"([^\"]*)\"",  "<$1");
      //Remove all SPAN  tags
      strContent = strContent.replaceAll("<\\/?SPAN[^>]*>", "");
      //Remove Lang attributes
      strContent = strContent.replaceAll("<(\\w[^>]*) lang=([^ |>]*)([^>]*)","<$1$3");
      //Remove Class attributes
      strContent = strContent.replaceAll("<(\\w[^>]*) class=([^ |>]*)([^>]*)", "<$1$3");
      //Remove XML elements and declarations
      strContent = strContent.replaceAll("<\\\\?\\?xml[^>]*>", "") ;
      //Remove Tags with XML namespace declarations: <o:p></o:p>
      strContent = strContent.replaceAll("<\\/?\\w+:[^>]*>", "");
      return strContent;
   }

   /**
    * 对组ID信息进行处理 转换为标准ID组 并过滤重复的信息
    * @param teamId
    * @return
    */
   public static String checkTeamId(String teamId) {
         String strTeamId = "";
         String strTempId = "";
         String strTemp = "";
         String[] arrTeamId = teamId.split(",");
         for(int num=0; num<arrTeamId.length; num++){
             strTemp=arrTeamId[num].trim();
             if((!strTemp.equals(""))&&(!strTemp.equals("0"))){
                 if ((strTempId.indexOf("," + strTemp + ",")) >= 0) { //表示已经保存过了
                 }else {
                     if(strTeamId.equals("")){
                        strTeamId=strTemp;
                        strTempId=strTempId+","+strTemp+",";;
                     }else{
                        strTeamId=strTeamId+","+strTemp;
                        strTempId=strTempId+strTemp+",";
                    }
                 }
             }
         }
         return strTeamId;
     }



   public static String replaceUbb(String content) {
	   String strContent = content;
       try{
           for (int num=0; num<arrTest.length ;num++ ){
               if ((strContent.indexOf(arrTest[num]))>= 0){
                   try{strContent = strContent.replaceAll(arrParam[num],arrCode[num]);}catch(Exception ex) {}
               }
           }
       }catch(Exception e) {
    	  //System.out.println("UBB CODE 错误"+e);
       }
	   return strContent;
   }
   
 
	/**
	 * 判断传入的字符串如果为null则返回"",否则返回其本身
	 * 
	 * @param string
	 * @param instant
	 * @return String
	 */
	public static String convertNull(String string, String instant) {
		return isNull(string) ? instant : string;
	}

	/**
	 * {@link #convertNull(String, String)}
	 * 
	 * @param string
	 * @return String
	 */
	public static String convertNull(String string) {
		return convertNull(string, "");
	}
   
	/**
	 * 判断对象是否为空
	 *
	 * @param obj
	 *            Object
	 * @return boolean 空返回true,非空返回false
	 */
	public static boolean isNull(Object obj) {
		return (null == obj) ? true : false;
	}
	/**
     * Description:判断字段空null <br>
     *
     * @param s
     * @return boolean
     */
    public static boolean isNull(String s)
    {
        if (s == null || "".equals(s.trim()))
        {
            return true;
        }
        
        return false;
    }
    
	/**
     * 获取百分比
     *
     *  @param  p1
     *  @param  p2
     *  @return
     */
    public   static  String percent( double  p1,  double  p2){
    	if(p2 == 0)
    	{
    		return "0.00%";
    	}
        String str;
        double  p3  =  p1  /  p2;
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        str  =  nf.format(p3);
        return  str;
    }

    /*
     * 单位转换
     * 厘转换元除以1000
     */

    public static String mobileFront(String money){
		if(null ==money || "".equals(money)) return money;
		BigDecimal big=new BigDecimal(money);
		big=big.divide(new BigDecimal("1000"));
		String re=big.toString();
		int d=re.indexOf(".");
		if(d> -1){
			if(d==re.length()-1)re+="00";
			else if(d==re.length()-2)re+="0";
		}
		else re+=".00";
		return re;
	}

    /*
     * 单位转换
     * 元转换厘乘以1000
     */
    public static String mobileBehind(String money){
		if(null ==money || "".equals(money)) return money;
		BigDecimal big=new BigDecimal(money);
		big=big.multiply(new BigDecimal("1000"));
		String res=big.toString();
		if(res.indexOf(".") == -1)
		{
			res+=".0";
		}
		return res.substring(0,res.indexOf("."));
	}
    /**
     * 设置请求CRM字符串长度
     * @param str
     * @return
     */
    public static String setRequestLenght(String str){
    	String result=null;
    	String temp;
    	int len = 0;
    	if(str==null){
    		return null;
    	}
    	if(str.indexOf("-----")<0){
    		return str;
    	}
    	len = str.length();
    	switch(String.valueOf(len).length()){
    	case 2:
    		temp = len+"   ";
    		break;
    	case 3:
    		temp = len+"  ";
    		break;
    	case 4:
    		temp = len+" ";
    		break;
    	case 5:
    		temp = len+"";
    		break;
    	default:
    		temp = "     ";
    		break;
    	}
    	result = str.replaceFirst("-----", temp);
    	return result;
    }



    public  static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s2="^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";// 验证手机号
        if(StringUtils.isNotBlank(str)){
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
