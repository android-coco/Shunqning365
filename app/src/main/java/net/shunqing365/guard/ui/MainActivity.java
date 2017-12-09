package net.shunqing365.guard.ui;

import android.view.View;
import android.widget.Button;

import net.shunqing365.guard.R;

import org.yh.library.ui.BindView;

import yh.org.shunqinglib.aty.LocationByGDActivity;
import yh.org.shunqinglib.base.BaseActiciy;
import yh.org.shunqinglib.utils.GlobalUtils;

public class MainActivity extends BaseActiciy
{


    @BindView(id = R.id.btn, click = true)
    Button btn;
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId())
        {
            case R.id.btn:
                GlobalUtils.HOME_HOST = "http://www.shunqing365.net";//接口地址
                GlobalUtils.DEIVER_SN = "A100004DC69883";//SN号
                showActivity(aty, LocationByGDActivity.class);
                break;
        }
    }

}
