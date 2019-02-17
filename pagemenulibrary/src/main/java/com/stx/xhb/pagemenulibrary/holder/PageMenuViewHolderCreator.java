package com.stx.xhb.pagemenulibrary.holder;

import android.view.View;

/**
 * time: 2019/2/17
 * mail:xhb_199409@163.com
 * github:https://github.com/xiaohaibin
 * describe:PageMenuViewHolderCreator
 * @author xiao.haibin
 */
public interface PageMenuViewHolderCreator {

    AbstractHolder createHolder(View itemView);

    int getLayoutId();
}
