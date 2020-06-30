package com.xwtech.xwecp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsiaInfoUtil {


    /**自己保存的私钥*/
    public static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDaQ96jjvSZjjjHtPovQn5KrSZ7z1OL8KWr5PTpZUbwdy72bgUL6O/Oosp8fPOxRpsMe7Kkc/2eZu4YAIFCuqqJZPbegFaM6AzizgSf+UiZSpf/Kbq+fV162PrtbQDLE3LPDc1R3rnuks3/hYbQ8auLQQFTtlldlRVFyrA8P2FVlPaqly+rv5drgllINbJM1FPxRpJpt3dl3f4Kwbw0jtScgbtXXXWF2Nk6aHGK2i/stCOucqF8i+jDxsv27bixPCtnc91Q/9OQ2gRonYIFHhqgzwLoGsX7/nuWxXAyIZF651s3Xhq+Rl3AgsrTOdYV4HCTbPDgzsgCeVVatRNyHCknAgMBAAECggEAX0UMoxu9DGGVyyXlpkdB1NqrV5Nxhj2uMs+nT1DTnBaJuyrerQop0mogNCJeRFzrRJGhskKmy0NmXbeqtDPCSGJ3lzsxjCOlrEH8ps8qry5uFgRi7M7PxNV9AbF4jSuaC2Kc4kVzpv0RSZ1A8WbrV8cyCxYm/8AZHvOP5VxM4gtqev3K9evun9SUjIq7UkrzOXeAactocI/sUPBAC9ikMSxPPwrflZ9ysKeNNgsgrOIir+rfDrm0ntLCrSv9n86Ht8jc1L9b8wKjl8DAoinb2MG7juHyOy6FOdkmQD2fPjgASnu56BVAaPu49AkDA+iUozvZ6Qrs8lr1PS8wCrKykQKBgQD/lXomTGx6F6S/3p44njWl5bqUTLEW622w8isqofWc1b3E11j5LRenm9YfRdBgIws+tYg8uWmFsuFT7aIZ6wvU/8b8dFBl7dqyJeg3wxNe+ED/1GBfBrbepLdTzRHBN+S5RYqPCmNsvNxePpSS8oGAyvhhHYxnOO8mK8b2VL9tMwKBgQDanta25jwAvv6L9fT6PJrfMLKCzNLz7NLgm0JGyuH01pncu3T0C98+CjpaKp4KEIw+NoaQ2Xbx0YYcalD7iwiNwwFe9ZkaUQ2wNJnm8yRBiFLWV/YHjMO8A4VCYWlhpsO3Icaen3jV7pUiiZEZbUJCNOENFDS9WJWNqtq8m5xMPQKBgDIA9rMDhL2mXo9DaMU+/tQETCqzjnojeYSC/LsMkNcsYkJet4OYtOYP8SaRLCGC+bI9Jfhrdh+4Talz9qEHgXscx7ugrhIjJQRxsismuWV9QIpM7MXqtGB+7XcxyeV5oAA3gNJXf/hIQv0VVVAirf+atlmZxFx1oSRgsD6FeiZNAoGAIAwev4r6isV0vQGeGrydm1AhKulplxZFR2WbDpOZEfIPdfsIxuminQ6UxTjwF9ubI1eM3Fj0hbhIJ4wyON2FsHpAjjIEcoYlMopTcTZ9PxoDwqX8S9kHrFz7wOJUt1rh38uUuRX5earSs9Bks8x/ZQMpZmhq3sLIkY7VJ8bL36UCgYEAk3KGENTDHv6C89hc2zgLv9FcjqdYX19hjft+d08HJhmhN/suUazruSoOi7+SSb/cUUilEdVOsVyk1yEJTwvMFjon4dMp1gHZifnjPn5J2MDGcgPNJKg973oCAD0Gc0WjFEPCnbbZnPGNX6uU5lOEYTAEkrW/eH/vffsa3cRYiyg=";

    /**调用接口的url   10.218.106.137:18080*/
//    public static final String URL = "http://10.218.106.137:18080/uip/open/api";
    public static final String URL = "https://www.ha.10086.cn/uip/open/api";
    /**接口编码--如“UIP_IMAGE_CONTRAS”由电渠统一接口平台提供*/
    public static final String  HANDING_BUS_SMS_CODE = "UIP_FX_BUSI_CONFIRM";
    /**接口编码--如“UIP_IMAGE_CONTRAS”由电渠统一接口平台提供*/
    public static final String BUSICODE_RSA_DESC = "UIP_ENCRYPT_KEY";
    /**接口编码--如“UIP_IMAGE_CONTRAS”由电渠统一接口平台提供*/
    public static final String BUSICODE_GOODS_KC = "UIP_GOOD_QUERY_STOCKNUMS";
    /**请求系统标识--如“3200”由电渠统一接口平台提供*/
    public static final String SOURCEID = "1010";
    /**业务参数格式*/
    public static final String FORMAT = "json";

    private static ThreadLocal<DateFormat> sf = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };
    private static ThreadLocal<DateFormat> sfSysDate = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    /**
     * 获取业务流水 busiSerial
     * @return
     */
    public static String busiSerial(){
        return SOURCEID+sf.get().format(new Date())+(int)((Math.random()*9+1)*100000);
    }
    public static String sysDate(){
        return sfSysDate.get().format(new Date());
    }
}
