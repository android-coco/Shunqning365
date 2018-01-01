package net.shunqing365.guard.ui;

import net.shunqing365.guard.R;
import net.shunqing365.guard.util.AppUtil;

import org.yh.library.utils.PreferenceUtils;
import org.yh.library.utils.StringUtils;

import yh.org.shunqinglib.aty.LocationByGD2Activity;
import yh.org.shunqinglib.base.BaseActiciy;
import yh.org.shunqinglib.utils.GlobalUtils;

/**
 * 启动类
 */
public class StartActivity extends BaseActiciy
{

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_start);
        isLogin();
    }

    private void isLogin()
    {
        String uid = PreferenceUtils.readString(aty, GlobalUtils.USER_XML, AppUtil.UID);
        if (StringUtils.isEmpty(uid))
        {
            skipActivity(aty, LoginActivity.class);
        }
        else
        {
            GlobalUtils.HOME_HOST = "http://www.shunqing365.net";//接口地址
            GlobalUtils.USER_UID = uid;//用户ID
            GlobalUtils.PACKAGE_STR = "net.shunqing365.guard";
            GlobalUtils.LOGIN_ACTIVITY_NAME ="net.shunqing365.guard.ui.LoginActivity";
            skipActivity(aty, LocationByGD2Activity.class);
        }
    }
}
