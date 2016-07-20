package com.tw.homework.Presenter;

import com.tw.homework.View.MainView;

/**
 * Created by Yasic on 2016/7/20.
 */
public class MainPresenter extends BasePresenter<MainView>{

    protected Class getBVIClass() {
        return MainView.class;
    }
}
