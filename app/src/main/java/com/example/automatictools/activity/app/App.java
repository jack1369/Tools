package com.example.automatictools.activity.app;

import android.app.Application;
import android.content.Context;
import com.xuexiang.xpage.PageConfig;
import com.xuexiang.xpage.PageConfiguration;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xutil.XUtil;

import java.util.List;

public class App  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        XUtil.init(this);
//        XUtil.debug(true);

//        PageConfig.getInstance().setPageConfiguration(new PageConfiguration() { //页面注册
//            @Override
//            public List<PageInfo> registerPages(Context context) {
//                return AppPageConfig.getInstance().getPages(); //自动注册页面
//            }
//        }).debug("PageLog").enableWatcher(true).init(this);
    }
}

