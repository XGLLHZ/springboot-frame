package org.huangzi.main.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.huangzi.main.common.config.DataConfig;

/**
 * @author: XGLLHZ
 * @date: 2019/9/16 9:40
 * @description: 短信工具类
 */
public class ShortMsgUtil {

    /**
     * 短信验证码（阿里云） 假设用户账号是手机号
     * @param mobile
     * @param code
     * @return 0：发送成功；1：发送失败
     */
    public static Integer sendSMsgCodeALi(String mobile, String code) {
        Integer result = 1;
        IClientProfile iClientProfile = DefaultProfile.getProfile("cn-hangzhou", DataConfig.getAccessKey(), DataConfig.getAccessSecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", DataConfig.getProduct(), DataConfig.getProductUrl());
            IAcsClient iAcsClient = new DefaultAcsClient(iClientProfile);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(mobile);
            request.setSignName(DataConfig.getSignName());
            request.setTemplateCode(DataConfig.getTemplateCode());
            request.setTemplateParam("\"code\":\"" + code + "\"}");
            SendSmsResponse response = iAcsClient.getAcsResponse(request);
            if (response.getCode() != null && "OK".equals(response.getCode())) {
                result = 0;
            } else {
                result = 1;
            }
        } catch(ClientException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 短信验证码（腾讯云） 假设用户账号为手机号
     * @param mobile
     * @param code
     * @return 0：发送成功；1：发送失败
     */
    public static Integer sendSMsgCodeTXun(String mobile, String[] code) {
        Integer result = 1;
        try {
            SmsSingleSender smsSingleSender = new SmsSingleSender(DataConfig.getAppId(), DataConfig.getAppKey());
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam(DataConfig.getSmsgCode(),
                    mobile, DataConfig.getTemplateId(), code, DataConfig.getSignNameId(), "", "");
            result = smsSingleSenderResult.result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        /*SYSUser sysUser = new SYSUser();
        sysUser.setUsername("13622119236");
        String[] code = RandomUtil.sixCodeArray();
        *//*ShortMsgUtil.sendSMsgCodeALi(sysUser, code);*//*
        ShortMsgUtil.sendSMsgCodeTXun(sysUser, code);*/
        String[] code = RandomUtil.sixCodeArray();
        ShortMsgUtil.sendSMsgCodeTXun("15193327839", code);
    }

}
