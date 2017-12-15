package net.shunqing365.guard.ui;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import net.shunqing365.guard.R;
import net.shunqing365.guard.util.AppUtil;

import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.PreferenceUtils;

import yh.org.shunqinglib.base.BaseActiciy;

/**
 * 添加终端
 */
public class AddTerminalActivity extends BaseActiciy
{

    // SN号输入框
    @BindView(id = R.id.phonenum)
    private AutoCompleteTextView number;
    @BindView(id = R.id.sendlongrep, click = true)
    private Button bh;
    private String uid;//用户ID
    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_add_terminal);
    }
    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("返回");
        toolbar.setMainTitle("绑定终端");
    }

    @Override
    public void initData()
    {
        super.initData();
        uid = PreferenceUtils.readString(aty, AppUtil.USER_XML, AppUtil.UID);
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        finish();
    }
    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.sendlongrep:
                add();
                break;
        }
    }

    private void add(){
        String params = "{\"uid\":\"" + uid + "\",\"sn\":\"" + number.getText().toString() + "\"}";
        YHRequestFactory.getRequestManger().postString(AppUtil.API_URL, AppUtil.TERMINAL_ADD, null, params, new
                HttpCallBack()
                {
                    @Override
                    public void onSuccess(String t)
                    {
                        super.onSuccess(t);
                        LogUtils.e(TAG,t);

                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg)
                    {
                        super.onFailure(errorNo, strMsg);
                        LogUtils.e(TAG, strMsg);
                    }

                    @Override
                    public void onFinish()
                    {
                        super.onFinish();
                    }
                }, TAG);
    }

}
