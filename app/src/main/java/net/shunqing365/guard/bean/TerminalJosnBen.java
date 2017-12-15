package net.shunqing365.guard.bean;

import com.google.gson.annotations.SerializedName;

import org.yh.library.bean.YHModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yhlyl on 2017/12/15.
 * 我的终端列表解析JSON实体类
 */

public class TerminalJosnBen extends YHModel
{
    @SerializedName("result")
    protected String resultCode;//结果Code
    @SerializedName("datas")
    protected List<TerminalModel> datas;//数据

    public static class TerminalModel implements Serializable
    {
        private String sn;//设备SN号
        @SerializedName("device_type")
        private String deviceType;//终端的型号类型
        private String keynum;//以逗号分隔，第一个为主亲情号
        private String keysos;//SOS求助号码
        private String latitude;//原始经度
        private String longitude;//原始纬度
        private String clatitude;//纠偏后的经度 （高德）
        private String address;//纠偏后的纬度 （高德）
        private String battery;//
        private String signal;
        @SerializedName("locate_type")
        private String locateType;
        @SerializedName("flag_power")
        private String flagPower;
        @SerializedName(" flag_battery ")
        private String  flagBattery ;
        private String datetime;

    }
}
