package net.shunqing365.guard.adapter;

import net.shunqing365.guard.bean.TerminalJosnBen;

import org.yh.library.adapter.rv.YhRecyclerAdapter;

/**
 * Created by yhlyl on 2017/12/15.
 * 我的设备列表
 */

public class TerminalAdapter extends YhRecyclerAdapter<TerminalJosnBen.TerminalModel>
{
    public TerminalAdapter(){
        addItemViewDelegate(new TerminalItemDelagate());
    }
}
