package net.shunqing365.guard.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shunqing365.guard.R;
import net.shunqing365.guard.adapter.TerminalAdapter;
import net.shunqing365.guard.bean.TerminalJosnBen;
import net.shunqing365.guard.util.AppUtil;

import org.yh.library.adapter.I_YHItemClickListener;
import org.yh.library.okhttp.YHRequestFactory;
import org.yh.library.okhttp.callback.HttpCallBack;
import org.yh.library.ui.BindView;
import org.yh.library.utils.LogUtils;
import org.yh.library.utils.PreferenceUtils;
import org.yh.library.view.YHAlertDialog;
import org.yh.library.view.YHRecyclerView;
import org.yh.library.view.yhrecyclerview.ProgressStyle;

import java.util.ArrayList;

import yh.org.shunqinglib.base.BaseActiciy;

/**
 * 我的终端列表
 */
public class TerminalActivity extends BaseActiciy implements
        I_YHItemClickListener<TerminalJosnBen.TerminalModel>
{

    @BindView(id = R.id.recyclerview)
    private YHRecyclerView mRecyclerView;
    @BindView(id = R.id.empty_layout)
    private LinearLayout empty_layout;
    @BindView(id = R.id.id_empty_text)
    private TextView id_empty_text;
    private TerminalAdapter mAdapter;
    ArrayList<TerminalJosnBen.TerminalModel> data = null;
    private String uid;//用户ID

    @Override
    public void setRootView()
    {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData()
    {
        super.initData();
        data = new ArrayList<>();
        uid = PreferenceUtils.readString(aty, AppUtil.USER_XML, AppUtil.UID);
    }

    @Override
    public void initWidget()
    {
        super.initWidget();
        toolbar.setLeftTitleText("");
        toolbar.setLeftTitleDrawable(R.mipmap.quit);
        toolbar.setMainTitle("我的终端");
        toolbar.setRightTitleText("");

        id_empty_text.setText("加载中。。。");
        //lineartlayout
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //分割线为LinearLayoutManager
        mRecyclerView.addItemDecoration(mRecyclerView.new YHItemDecoration());//分割线
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulseRise);//可以自定义下拉刷新的样式
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);//可以自定义上拉加载的样式
        mRecyclerView.setFootViewText(getString(R.string.listview_loading), "我是有底线的。");
        // mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);//箭头
        mAdapter = new TerminalAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLoadingMoreEnabled(false);
//        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingListener(new YHRecyclerView.LoadingListener()
        {
            @Override
            public void onRefresh()
            {
//                page = 1;
                mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                getData();
            }

            @Override
            public void onLoadMore()
            {
//                //page++;
//                if (page <= TOTAL_PAGE) {//小于总页数就加载更多
//                    // loading more
//                    getData();
//                } else {
//                    //the end
//                    mRecyclerView.setNoMore(true);
//                }
            }
        });
        mRecyclerView.refresh();
    }

    @Override
    protected void onMenuClick()
    {
        super.onMenuClick();
        showActivity(aty, AddTerminalActivity.class);
    }

    @Override
    protected void onBackClick()
    {
        super.onBackClick();
        new YHAlertDialog(aty).builder().setMsg("是否退出当前账号").setNegativeButton("确定", new View
                .OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        }).setPositiveButton("取消", new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        }).show();
    }

    @Override
    public void widgetClick(View v)
    {
        super.widgetClick(v);
//        switch (v.getId())
//        {
//            case R.id.btn:
//                GlobalUtils.HOME_HOST = "http://www.shunqing365.net";//接口地址
//                GlobalUtils.DEIVER_SN = "A100004DC69883";//SN号
//                showActivity(aty, LocationByGDActivity.class);
//                break;
//        }
    }

    private void getData()
    {
        String params = "{\"uid\":\"" + uid + "\"}";
        YHRequestFactory.getRequestManger().postString(AppUtil.API_URL, AppUtil.TERMINAL_LIST,
                null, params, new
                        HttpCallBack()
                        {
                            @Override
                            public void onSuccess(String t)
                            {
                                super.onSuccess(t);
                                LogUtils.e(TAG, t);

//                        final JsonMdrSdModel jsonData = JsonUitl.stringToTObject
//                                (t, JsonMdrSdModel.class);
//                        String resultCode = jsonData.getResultCode();
//                        if ("0".equals(resultCode))
//                        {
//                            if (StringUtils.isEmpty(jsonData.getDatas()))
//                            {
//                                id_empty_text.setText("暂无数据!");
//                                mRecyclerView.setEmptyView(empty_layout);//没有数据的空布局
//                            } else
//                            {
//                                data.addAll(jsonData.getDatas());
//                                mAdapter.setDatas(data);
//                            }
//                        } else
//                        {
//                            id_empty_text.setText("Code:" + resultCode);
//                            mRecyclerView.setEmptyView(empty_layout);
//                        }
//                        //刷新完毕
//                        mRecyclerView.refreshComplete();
                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg)
                            {
                                super.onFailure(errorNo, strMsg);
                                LogUtils.e(TAG, strMsg);
                                id_empty_text.setText("加载失败");
                                mRecyclerView.setEmptyView(empty_layout);
                                mAdapter.getDatas().clear();//必须在数据更新前清空，不能太早
                                //刷新完毕
                                mRecyclerView.refreshComplete();
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFinish()
                            {
                                super.onFinish();
                            }
                        }, TAG);
    }

    @Override
    public boolean onItemLongClick(View view, TerminalJosnBen.TerminalModel data, int position)
    {
        return false;
    }

    @Override
    public void onItemClick(View view, TerminalJosnBen.TerminalModel data, int position)
    {

    }
}
