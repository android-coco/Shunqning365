package net.shunqing365.guard.adapter;

import net.shunqing365.guard.R;
import net.shunqing365.guard.bean.TerminalJosnBen;

import org.yh.library.adapter.rv.I_ItemViewDelegate;
import org.yh.library.adapter.rv.YHRecyclerViewHolder;

/**
 * Created by yhlyl on 2017/12/15.
 */

public class TerminalItemDelagate implements I_ItemViewDelegate<TerminalJosnBen.TerminalModel>
{
    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.terminal_item;
    }

    @Override
    public boolean isForViewType(TerminalJosnBen.TerminalModel item, int position)
    {
        return false;
    }

    @Override
    public void convert(YHRecyclerViewHolder holder, TerminalJosnBen.TerminalModel item, int position)
    {

    }
}
