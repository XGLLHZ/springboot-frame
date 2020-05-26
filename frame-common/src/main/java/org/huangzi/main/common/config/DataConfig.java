package org.huangzi.main.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: XGLLHZ
 * @date: 2020/5/26 上午9:40
 * @description: yml 数据配置
 * yml 文件里配置的属性正常情况下在实体类中只有非静态属性才可以取到
 * 当然如果想静态调用的话，可以将实体类中的属性定义为静态，然后再在非静态的 set 方法中设置
 * 这样就可以通过静态的 get 方法太调用
 */
@Configuration
public class DataConfig {

    /**
     * 邮件（163）
     */
    private static String fromAccount;   //发送发账号

    private static String grantCode;   //授权码

    /**
     * 短信（阿里、腾讯）
     */
    private static String product;   //产品名称

    private static String productUrl;   //产品域名

    private static String accessKey;   // key id

    private static String accessSecret;   // key 秘钥

    private static String signName;   //短信签名

    private static String templateCode;   //短信模板

    private static Integer appId;   // app_id

    private static String appKey;   // app_key

    private static String signNameId;   //签名

    private static Integer templateId;   //模板 id

    private static Integer pronhubId;   //pronhub 模板 id

    private static String smsgCode;   //区号

    /**
     * OSS 对象存储（阿里）
     */
    private static String endPoint;   // end point

    private static String keyId;   // access_key_id

    private static String keySecret;   // access_key_id

    private static String bucket;   // bucket

    private static String fileDir;   //文件保存基路径

    private static String imageUrl;   //图片路径

    @Value("${common.email.fromAccount}")
    public void setFromAccount(String fromAccount) {
        DataConfig.fromAccount = fromAccount;
    }

    @Value("${common.email.grantCode}")
    public void setGrantCode(String grantCode) {
        DataConfig.grantCode = grantCode;
    }

    @Value("${common.msg.ali.product}")
    public void setProduct(String product) {
        DataConfig.product = product;
    }

    @Value("${common.msg.ali.productUrl}")
    public void setProductUrl(String productUrl) {
        DataConfig.productUrl = productUrl;
    }

    @Value("${common.msg.ali.accessKey}")
    public void setAccessKey(String accessKey) {
        DataConfig.accessKey = accessKey;
    }

    @Value("${common.msg.ali.accessSecret}")
    public void setAccessSecret(String accessSecret) {
        DataConfig.accessSecret = accessSecret;
    }

    @Value("${common.msg.ali.signName}")
    public void setSignName(String signName) {
        DataConfig.signName = signName;
    }

    @Value("${common.msg.ali.templateCode}")
    public void setTemplateCode(String templateCode) {
        DataConfig.templateCode = templateCode;
    }

    @Value("${common.msg.tencent.appId}")
    public void setAppId(Integer appId) {
        DataConfig.appId = appId;
    }

    @Value("${common.msg.tencent.appKey}")
    public void setAppKey(String appKey) {
        DataConfig.appKey = appKey;
    }

    @Value("${common.msg.tencent.signNameId}")
    public void setSignNameId(String signNameId) {
        DataConfig.signNameId = signNameId;
    }

    @Value("${common.msg.tencent.templateId}")
    public void setTemplateId(Integer templateId) {
        DataConfig.templateId = templateId;
    }

    @Value("${common.msg.tencent.pronhubId}")
    public void setPronhubId(Integer pronhubId) {
        DataConfig.pronhubId = pronhubId;
    }

    @Value("${common.msg.tencent.smsgCode}")
    public void setSmsgCode(String smsgCode) {
        DataConfig.smsgCode = smsgCode;
    }

    @Value("${common.oss.endPoint}")
    public void setEndPoint(String endPoint) {
        DataConfig.endPoint = endPoint;
    }

    @Value("${common.oss.keyId}")
    public void setKeyId(String keyId) {
        DataConfig.keyId = keyId;
    }

    @Value("${common.oss.keySecret}")
    public void setKeySecret(String keySecret) {
        DataConfig.keySecret = keySecret;
    }

    @Value("${common.oss.bucket}")
    public void setBucket(String bucket) {
        DataConfig.bucket = bucket;
    }

    @Value("${common.oss.fileDir}")
    public void setFileDir(String fileDir) {
        DataConfig.fileDir = fileDir;
    }

    @Value("${common.oss.imageUrl}")
    public void setImageUrl(String imageUrl) {
        DataConfig.imageUrl = imageUrl;
    }

    public static String getFromAccount() {
        return fromAccount;
    }

    public static String getGrantCode() {
        return grantCode;
    }

    public static String getProduct() {
        return product;
    }

    public static String getProductUrl() {
        return productUrl;
    }

    public static String getAccessKey() {
        return accessKey;
    }

    public static String getAccessSecret() {
        return accessSecret;
    }

    public static String getSignName() {
        return signName;
    }

    public static String getTemplateCode() {
        return templateCode;
    }

    public static Integer getAppId() {
        return appId;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static String getSignNameId() {
        return signNameId;
    }

    public static Integer getTemplateId() {
        return templateId;
    }

    public static Integer getPronhubId() {
        return pronhubId;
    }

    public static String getSmsgCode() {
        return smsgCode;
    }

    public static String getEndPoint() {
        return endPoint;
    }

    public static String getKeyId() {
        return keyId;
    }

    public static String getKeySecret() {
        return keySecret;
    }

    public static String getBucket() {
        return bucket;
    }

    public static String getFileDir() {
        return fileDir;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

}
