package org.huangzi.main.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.huangzi.main.common.entity.AccessToken;
import org.huangzi.main.common.mapper.AccessTokenMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.token.Token;

/**
 * @author: XGLLHZ
 * @date: 2019/11/26 下午3:31
 * @description: 微信工具类（此工具类将被重写）
 */
@Component
public class WeiXinUtil extends Thread {

    /**
     * 微信网页授权时，刷新 access_token，一小时一次，因为 微信官方设置 access_token 两小时过期，
     * 但同一个 app_id 每天获取 access_token 的次数有限制，大概为一百次好像，所以我们这里设置为一小时刷新一次，
     * 一天也就二十四次。
     * 此工具类应用时需要创建 sys_access_token 表来存放 access_token。
     */

    @Autowired
    AccessTokenMappper accessTokenMappper;

    public static AccessToken accessToken = new AccessToken();

    /**
     * 服务启动时时执行
     */
    /*@PostConstruct*/
    public void init() {
        accessToken = accessTokenMappper.selectOne(new QueryWrapper<AccessToken>()
                .eq("delete_flag", 0));
        this.start();
    }

    /**
     * 重写线程方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 1; i--) {   //进入此循环后不退出，一直运行
            try {
                sleep(60 * 60 * 1000);   //项目启动后线程先睡眠一小时，然后在运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (accessToken != null) {   //若数据库中有 access_token 记录，则刷新（因为这个方法没有返回 refreshToken，所以实际上是重新创建）
                Token token = TokenAPI.token(ConstConfig.APP_ID, ConstConfig.APP_SECRET);
                accessToken.setAccessToken(token.getAccess_token());
                Integer res = accessTokenMappper.updateById(accessToken);
                if (res > 0) {
                    System.out.println("刷新 access_token 成功！");
                } else {
                    System.out.println("刷新 access_token 失败！");
                }
            } else {   //若数据库中无 access_token 记录，则创建一个 access_token
                Token token = TokenAPI.token(ConstConfig.APP_ID, ConstConfig.APP_SECRET);
                accessToken.setAccessToken(token.getAccess_token());
                Integer res = accessTokenMappper.insert(accessToken);
                if (res > 0) {
                    System.out.println("创建 access_token 成功！");
                } else {
                    System.out.println("创建 access_token 失败！");
                }
            }
        }
    }

}
