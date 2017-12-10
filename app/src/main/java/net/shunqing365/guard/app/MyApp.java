package net.shunqing365.guard.app;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import yh.org.shunqinglib.app.ShunQingApp;

/**
 * Created by yhlyl on 2017/12/9.
 */

public class MyApp extends ShunQingApp
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        PlatformConfig.setWeixin("wx2c2cbc1f19585fbf","be0c1c3e86fe7298748fa46087d73cb8");
        PlatformConfig.setQQZone("101057756","294c113931c7a863460ccbbc9f3bf0c5");
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
    }

    //各个平台的配置，建议放在全局Application或者程序入口
   {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
