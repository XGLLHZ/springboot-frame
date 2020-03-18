package org.huangzi.main.common.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/2/15 下午12:56
 * @description: 支付宝工具类
 */
public class AliPayUtil {

    //应用 appId
    private static final String APP_ID = "20161014006869100";

    //商户私钥
    private static final String PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCc3b4I9x5v4cQk+IH2x5aqio4iDHIl3gDvVrKdtv28fQyI6qbU77r8DSHSLRnyR4NbMKvTeQWA/hA1hpl+ehWBSdCNJWb6mBZS+Fla90CUNy2gZpATQYSLcKKUYDh3hVDxBhoTr38NO4BhYRwhHPy6tuim+2PSgrFsolSFdHdeXmZDIdjQcuY9wWuut2G+AFyMgJpFh5DQzkJcpyEkHUSzaKgcEcA2lKl+wcfaFnQsW/BfvzC1P2itnBj2X6WlnlUCW1ffbew4jK4BorHLzX9Dq57JW6lUU8CJS5hW2LYCC8btrD7eGICNvJceBSAxGL9GPl5ogqrhnmDndtcy1FtDAgMBAAECggEAKImFGnwB+Ze2QHeX3lYlsVys7k+ZEAAW7cLR1b/8QkFug9Nf2Zc6EvixzMaq9dlVMLObM8MKkRMWzT5DXFPgStDwfAk2W3wCCN70+HT801CPEexvPwqnXgF1gjJ7ZSemiyKwinXHt3uw6XlW85eyF+48oSpF2Zi8kP5CKVq3nkMOB8oP9UQztF0EiiYh6kTGQcooO1tMLVPC4Gog3ftqcXAE7rhz24lYviKriv9LrgoCePyrzburkRej9fKGfp3yVUc01XlCw0suWVlaPa09bU4h1Bv+dxyjm6LKbPuhM9oW84Tf8X9W77tkR1wd59dYQQvPKivgcVvyaXJshbFEgQKBgQDqB5K4tNGQN41bKdGUjYcysyJrMeFQM+AnNp/oB1Mhj3voLsFDVDhbz5WltSi4pz7u33aGyol5W9B0xxAGb2+gp2ewoFbI/1pWtx6PYZ8JuCqpA35kiQ+NGRI6xtlO16Li6vo2xbg5KMAGGkRv30Xe3E3Be5fiUrHWBESIjiRH6wKBgQCrl7QC+ZZRLbw2n5T2LVlaHsXoX2qkm/awTtNTl/LDDUZCbM9M0P1P3mcerDOlyyoO4Oqhx0mQzfAVplMuu70FFcJInJhIZlVDgv43G5ZokUEf0jtPY3cR0Rwhdzy9ZMRjn5lubE84xsfIPHX/d1ow8cyS60ySqsqgVKw7LCh8CQKBgQCTriu8Ov5zT1r4SC9k2tqR99CkbFmOTu4IUMQfm1+uMeky3y492oumBBye8O69d2yTOJiG/pA58OJejq9iIDgE3R7lqoiZVeogBnZWbHf7niftd7Lupj8V/tguFwVSZ2j2YOl8XtCd4UUrlK/TGMXymHrIucFipGRsdUz6Dxh9+QKBgQCaqbdAQzUA4KdX2MkMh12VRE2AIJjf3BAjZDlIjJnsw4dDkg3boDfiiIUL+Yzn8FJtbL8vqJAfa+/to6gwZFmAPteKWVNNVqednXGDvGL6x4oao4SADxggwAmKBI6D61K0tQM3q3pQo6MpUY3OAQcTPK728g1n2XefwhInl6q5+QKBgQC6ucvEiSbcgtpHVKlebPkJ8YWjTE8Vs+uJHK/BIn92Nbm0WVjm58FnLuSq6TMDFK7roMw5OQ+0lRfFlvpbixyB+SmTtVOX9GKQ9QojN+J88/UTbxUdF0eEDVjXhRQecNDA5hS1kl8ker8mGLZ/4IzuqdYkBZIctkEoUIseDoUd6Q0==";

    //支付宝公钥
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzbX2ahDqHN/lKD82Lpsr63Frytq297OQzLOFFIQmH49wRMFLxyeHN0gzYJ3uxi9GjahcnczBuRO7qc8XQXf4uuslxxNclqjQZoZqTwd74TPrCgKnA+m9nKB+l7FkrKyXDniG2zKOi6odoYIxRL5M3DlhiImKiR1EobWQKFniNWECiQL1Q8RjbZV02G4q64MGeU2GOCuFeTup79J7WLMg2MJheoHqElPQWr9Rt3h5+oQsatZVodun9+X03YXUY69vWkAY67vbXVdTStuvx6utL4sZw+3Ob1icRaZKUjGUYpQ1j7XIGTR1wE/FfRBSYViHBBJ0iYkowZvNQ9dPjDE2xwIDAQAB0";

    //支付宝网关
    private static final String GATE_WAY = "https://openapi.alipaydev.com/gateway.do";

    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    //编码
    public static final String CHARSET = "utf-8";

    //类型
    private static final String FORMAT = "JSON";

    //支付成功后返回的页面
    private static final String RETURN_URL = "http://127.0.0.1:8081/api/admin/alipay/return";

    //异步通知地址
    private static final String NOTIFY_URL = "http://127.0.0.1:8081/api/admin/alipay/notify";

    //沙箱测试商户号
    public static final String USER_NO = "cgwild8409@sandbox.com";

    /**
     * PC 网页支付
     * @param outTradeNo 订单号
     * @param shopName 商品名
     * @param shopPrice 商品价格
     * @param shopBody 商品描述
     * @return
     * @throws AlipayApiException
     */
    public static String toPcPay(String outTradeNo, String shopName, String shopPrice, String shopBody) throws AlipayApiException {
        //创建 支付请求对象
        AlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY, APP_ID, PRIVATE_KEY, FORMAT, CHARSET, PUBLIC_KEY, SIGN_TYPE);
        //创建 api 对应的 request（电脑网页版）
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);
        //添加订单参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + outTradeNo + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + shopPrice + "," +
                "    \"subject\":\"" + shopName + "\"," +
                "    \"body\":\""+ shopBody + "\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"" + USER_NO + "\"" +
                "    }"+
                "  }");
        //调用 sdk 生成表单，通过 get  方式，接口可或得 url
        return alipayClient.pageExecute(request, "GET").getBody();
    }

    /**
     * Mobile 网页支付
     * @param outTradeNo 订单号
     * @param shopName 商品名
     * @param shopPrice 商品价格
     * @param shopBody 商品描述
     * @return
     * @throws AlipayApiException
     */
    public static String toMobilePay(String outTradeNo, String shopName, String shopPrice, String shopBody) throws AlipayApiException {
        //创建 支付请求对象
        AlipayClient alipayClient = new DefaultAlipayClient(GATE_WAY, APP_ID, PRIVATE_KEY, FORMAT, CHARSET, PUBLIC_KEY, SIGN_TYPE);
        //创建 api 对应的 request（移动网页版）
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);
        //添加订单参数
        request.setBizContent("{" +
                "    \"out_trade_no\":\"" + outTradeNo + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + shopPrice + "," +
                "    \"subject\":\"" + shopName + "\"," +
                "    \"body\":\""+ shopBody + "\"," +
                "    \"sys_service_provider_id\":\"" + USER_NO + "\"" +
                "  }");
        //调用 sdk 生成表单，通过 get  方式，接口可或得 url
        return alipayClient.pageExecute(request, "GET").getBody();
    }

    /**
     * 生成订单号
     * @return
     */
    public static String createOrderNo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = simpleDateFormat.format(new Date());
        String s1 = RandomUtil.sixCode();
        return s + s1;
    }

    /**
     * 校验签名
     * @param request
     * @return
     */
    public static boolean checkSign(HttpServletRequest request) {
        //获取支付宝 post 过来的信息
        Map params = request.getParameterMap();
        Map<String, String> map = new HashMap<>(1);
        for (Object o : params.keySet()) {
            String key = (String) o;
            String[] strings = (String[]) params.get(key);
            String s = "";
            for (int i = 0; i < strings.length; i++) {
                s = (i == strings.length - 1) ? s + strings[i] : s + strings[i] + ",";
            }
            map.put(key, s);
        }
        try {
            return AlipaySignature.rsaCheckV1(map, PUBLIC_KEY, CHARSET, SIGN_TYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
    }

}
