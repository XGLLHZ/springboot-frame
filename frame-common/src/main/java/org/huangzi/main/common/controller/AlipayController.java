package org.huangzi.main.common.controller;

import com.alipay.api.AlipayApiException;
import org.huangzi.main.common.entity.OrderEntity;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.AliPayUtil;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.common.enums.AliPayEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/2/15 下午4:33
 * @description: 支付宝支付前端控制器 样例
 */
@RestController
@RequestMapping("/admin/alipay")
public class AlipayController {

    @RequestMapping("/pay")
    public APIResponse aliPay(@RequestBody OrderEntity orderEntity) {
        String outTradeNo = AliPayUtil.createOrderNo();
        Map<String, Object> map = new HashMap<>(1);
        try {
            String url = AliPayUtil.toPcPay(outTradeNo, orderEntity.getShopName(), orderEntity.getShopPrice(), orderEntity.getShopBody());
            map.put("dataInfo", url);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse(map);
    }

    @GetMapping("/return")
    public APIResponse returnPage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=" + AliPayUtil.CHARSET);
        Map<String, Object> map = new HashMap<>(2);
        //内容校验 防止黑客篡改参数
        if (AliPayUtil.checkSign(request)) {
            //商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品名称
            //String shopName = new String(request.getParameter("subject").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品价格
            String shopPrice = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品描述
            //String shopBody = new String(request.getParameter("body").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setTradeNo(tradeNo);
            orderEntity.setOutTradeNo(outTradeNo);
            //orderEntity.setShopName(shopName);
            orderEntity.setShopPrice(shopPrice);
            //orderEntity.setShopBody(shopBody);
            map.put("dataInfo", orderEntity);
            //用户获取到的商户订单号获取订单信息，然后将商品及支付相关信息返回前台
            return new APIResponse(map);
        } else {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
    }

    @RequestMapping("/notify")
    public APIResponse notify(HttpServletRequest request) {
        //获取支付宝 post 的反馈信息
        Map<String, String[]> map = request.getParameterMap();
        //内容校验，防止黑客篡改参数
        if (AliPayUtil.checkSign(request)) {
            //商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品名称
            String shopName = new String(request.getParameter("subject").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品价格
            String shopPrice = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //商品描述
            String shopBody = new String(request.getParameter("body").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //订单状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            //验证
            if (tradeStatus.equals(AliPayEnum.SUCCESS.getValue()) || tradeStatus.equals(AliPayEnum.FINISHED.getValue())) {

                //修改订单状态

            }
            return new APIResponse();
        }
        return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
    }

}
