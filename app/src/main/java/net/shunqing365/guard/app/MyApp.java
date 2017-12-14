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
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
    }

    //各个平台的配置，建议放在全局Application或者程序入口
   {
        PlatformConfig.setWeixin("wx22a40574ac7135d5", "ee5aa2bc82e047bbc04eb9844e313688");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
