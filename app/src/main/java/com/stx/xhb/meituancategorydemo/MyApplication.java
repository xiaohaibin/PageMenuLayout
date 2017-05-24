package com.stx.xhb.meituancategorydemo;

import android.app.Application;

import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;

/**
 * Author: Mr.xiao on 2017/5/24
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.init(this);
    }
}
