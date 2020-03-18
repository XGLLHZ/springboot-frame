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

/**
 * @author: XGLLHZ
 * @date: 2019/9/16 9:40
 * @description: 短信工具类
 */
public class ShortMsgUtil {

    private static final String ALI_PRODUCT = "Dysmsapi";   //产品名称

    private static final String ALI_PRODUCT_URL = "dysmsapig.aliyuncs.com";   //产品域名

    private static final String ALI_ACCESS_KEY_ID = "LTAI4FhArnvkXAAn2ThGV9dv";   //key id

    private static final String ALI_ACCESS_KEY_SECRET = "lg2RVU0TkjV19oyqrhh9UiiBtPy7Vu";   //key 密钥

    private static final String ALI_SIGN_NAME = "人世间子";   //短信签名

    private static final String ALI_TEMPLATE_CODE = "SMS_174023364";   //短信模板

    private static final Integer TXUN_APP_ID = 1400258417;   //应用 app_id

    private static final String TXUN_APP_KEY = "5efe519b58570b4e691a628cabae5f69";   //应用 app_key

    private static final String TXUN_SIGN_NAME_ID = "人世间子";   //签名 id

    private static final Integer TXUN_TEMPLATE_ID = 420791;   //模板 id

    private static final Integer PRONHUB = 420843;

    private static final String TXUN_SMSG_CODE = "86";   //区号

    /**
     * 短信验证码（阿里云） 假设用户账号是手机号
     * @param mobile
     * @param code
     * @return 0：发送成功；1：发送失败
     */
    public static Integer sendSMsgCodeALi(String mobile, String code) {
        Integer result = 1;
        IClientProfile iClientProfile = DefaultProfile.getProfile("cn-hangzhou", ALI_ACCESS_KEY_ID, ALI_ACCESS_KEY_SECRET);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", ALI_PRODUCT, ALI_PRODUCT_URL);
            IAcsClient iAcsClient = new DefaultAcsClient(iClientProfile);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(mobile);
            request.setSignName(ALI_SIGN_NAME);
            request.setTemplateCode(ALI_TEMPLATE_CODE);
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
            SmsSingleSender smsSingleSender = new SmsSingleSender(TXUN_APP_ID, TXUN_APP_KEY);
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam(TXUN_SMSG_CODE,
                    mobile, TXUN_TEMPLATE_ID, code, TXUN_SIGN_NAME_ID, "", "");
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
