package net.shunqing365.guard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.shunqing365.guard.R;
import net.shunqing365.guard.adapter.CustomDialogFactory;
import net.shunqing365.guard.util.AppUtil;
import net.shunqing365.guard.view.ZQImageViewRoundOval;

import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.ui.YHViewInject;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.StringUtils;
import org.yh.library.view.loading.dialog.YHLoadingDialog;
import org.zackratos.ultimatebar.UltimateBar;

import java.util.Map;

import yh.org.shunqinglib.base.BaseActiciy;

/**
 * 登录
 */
public class LoginActivity extends BaseActiciy
{
    //    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
//    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN,
//            SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER, SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.DOUBAN, SHARE_MEDIA.RENREN, SHARE_MEDIA.KAKAO,
//            SHARE_MEDIA.VKONTAKTE, SHARE_MEDIA.DROPBOX};
    @BindView(id = R.id.login_winxin, click = true)
    TextView login_winxin;//登录
    @BindView(id = R.id.img_bg)
    private ZQImageViewRoundOval iv_roundRect;//圆角矩形图片
    UMShareAPI umShareAPI;

    @Override
    public void setRootView()
    {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setHintBar();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initData()
    {
        super.initData();
        //initPlatforms();
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        umShareAPI = UMShareAPI.get(aty);
        umShareAPI.setShareConfig(config);
    }

    //    private void initPlatforms() {
//        platforms.clear();
//        for (SHARE_MEDIA e : list) {
//            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
//                platforms.add(e.toSnsPlatform());
//            }
//        }
//    }

    UMAuthListener authListener = new UMAuthListener()
    {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform)
        {
            YHLoadingDialog.make(aty, new CustomDialogFactory()).setMessage("授权中").show();
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data)
        {
            YHLoadingDialog.cancel();
            YHLoadingDialog.make(aty, new CustomDialogFactory()).setMessage("登录中").show();
            if (!StringUtils.isEmpty(data)){
                login(data);
            }else{
                YHViewInject.create().showTips("无法获取用户信息！");
            }

            LogUtils.e(TAG, data);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t)
        {
            YHViewInject.create().showTips("授权失败：" + t.getMessage());
            YHLoadingDialog.cancel();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action)
        {
            YHViewInject.create().showTips("取消登录");
            YHLoadingDialog.cancel();
        }
    };

    /**
     * 登录
     * @param data  用户信息
     */
    private void login(Map<String, String> data)
    {
        String params = "{\"unionid\":\"" + data.get("unionid") + "\",\"openid\":\"" +
                data.get("openid") + "\"}";
        YHRequestFactory.getRequestManger().postString(AppUtil.API_URL, AppUtil.LOGIN, null, params, new HttpCallBack()
        {
            @Override
            public void onSuccess(String t)
            {
                super.onSuccess(t);
                YHViewInject.create().showTips("登录成功");
            }

            @Override
            public void onFailure(int errorNo, String strMsg)
            {
                super.onFailure(errorNo, strMsg);
                YHViewInject.create().showTips("登录失败");
            }

            @Override
            public void onFinish()
            {
                super.onFinish();
                YHLoadingDialog.cancel();
            }
        }, TAG);
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
                umShareAPI.getPlatformInfo(aty, SHARE_MEDIA.WEIXIN, authListener);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (!StringUtils.isEmpty(umShareAPI))
        {
            umShareAPI.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (!StringUtils.isEmpty(umShareAPI))
        {
            umShareAPI.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (!StringUtils.isEmpty(umShareAPI))
        {
            umShareAPI.release();
        }

    }
}
