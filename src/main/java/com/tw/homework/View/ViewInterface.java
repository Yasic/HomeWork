package com.tw.homework.View;

import com.tw.homework.Presenter.BasePresenter;

/**
 * Created by Yasic on 2016/7/20.
 */
public interface ViewInterface<T extends BasePresenter> {
    ViewInterface getView();
}
