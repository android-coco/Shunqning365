package net.shunqing365.guard.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

/**
 * Created by yhlyl on 2017/12/15.
 * 登陆结果解析实体类
 */

public class LoginJosnBen extends YHModel
{
    @SerializedName("result")
    protected String resultCode;//结果Code
    @SerializedName("uid")
    protected String uid;//用户id

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    @Override
    public String toString()
    {
        return "LoginJosnBen{" +
                "resultCode='" + resultCode + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
