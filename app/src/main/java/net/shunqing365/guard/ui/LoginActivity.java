package net.shunqing365.guard.ui;

import android.view.View;
import android.widget.TextView;

import net.shunqing365.guard.R;
import net.shunqing365.guard.view.ZQImageViewRoundOval;

import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.zackratos.ultimatebar.UltimateBar;

import yh.org.shunqinglib.base.BaseActiciy;

/**
 * 登录
 */
public class LoginActivity extends BaseActiciy
{

    @BindView(id = R.id.login_winxin, click = true)
    TextView login_winxin;//登录
    @BindView(id = R.id.img_bg)
    private ZQImageViewRoundOval iv_roundRect;//圆角矩形图片
    @Override
    public void setRootView()
    {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setHintBar();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        iv_roundRect.setType(ZQImageViewRoundOval.TYPE_ROUND);
        iv_roundRect.setRoundRadius(20);//矩形凹行大小
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.login_winxin:
                YHViewInject.create().showTips("登录");
                break;
        }
    }
}
