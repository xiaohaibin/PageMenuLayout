package com.stx.xhb.pagemenulibrary.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * time: 2019/2/17
 * mail:xhb_199409@163.com
 * github:https://github.com/xiaohaibin
 * describe: AbstractHolder
 * @author xiao.haibin
 */
public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    protected abstract void initView(View itemView);

    public abstract void bindView(RecyclerView.ViewHolder holder,T data,int pos);
}
