package com.example.automatictools.activity.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.automatictools.R;

public class ToolFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //解析Fragment布局文件
        view =inflater.inflate(R.layout.tool_fragment, null);
        //获取listView控件
        return  view;
    }

}
