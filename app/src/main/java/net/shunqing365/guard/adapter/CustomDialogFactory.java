package net.shunqing365.guard.adapter;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import net.shunqing365.guard.R;

import org.yh.library.view.loading.factory.I_DialogFactory;

/**
 * 作者：游浩 on 2017/12/14 13:52
 * https://github.com/android-coco/YhLibraryForAndroid
 * 邮箱：yh_android@163.com
 */
public class CustomDialogFactory implements I_DialogFactory
{
    @Override
    public Dialog onCreateDialog(Context context)
    {
        Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.setContentView(R.layout.layout_custom);
        return dialog;
    }

    @Override
    public void setMessage(Dialog dialog, CharSequence message)
    {
        TextView tv = dialog.findViewById(R.id.tv_message);
        if (tv != null)
        {
            tv.setText(message);
        }
    }

    @Override
    public int getAnimateStyleId()
    {
        return R.style.Dialog_Alpha_Animation;
    }
}
