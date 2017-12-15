package net.shunqing365.guard.ui;

import net.shunqing365.guard.R;
import net.shunqing365.guard.util.AppUtil;

import org.yh.library.utils.PreferenceUtils;
import org.yh.library.utils.StringUtils;

import yh.org.shunqinglib.base.BaseActiciy;

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
        String uid = PreferenceUtils.readString(aty, AppUtil.USER_XML, AppUtil.UID);
        if (StringUtils.isEmpty(uid))
        {
            skipActivity(aty, LoginActivity.class);
        }
        else
        {
            skipActivity(aty, TerminalActivity.class);
        }
    }
}
