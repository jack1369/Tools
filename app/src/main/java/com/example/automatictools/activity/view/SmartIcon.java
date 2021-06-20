/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.automatictools.activity.view;

import android.content.Context;
import android.widget.ImageView;

import com.example.automatictools.R;
import com.xuexiang.xfloatview.XFloatView;


/**
 * 悬浮图标
 *
 * @author xuexiang
 * @since 2018/9/13 下午2:14
 */
public class SmartIcon extends XFloatView {

    public SmartIcon(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_smarticon;
    }

    @Override
    protected boolean canMoveOrTouch() {
        return true;
    }

    @Override
    public void initFloatView() {
        setRotateView((ImageView) findViewById(R.id.ic_show), R.drawable.ic_show_normal);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isAdsorbView() {
        return true;
    }

}
