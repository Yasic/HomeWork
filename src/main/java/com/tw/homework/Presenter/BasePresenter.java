package com.tw.homework.Presenter;

import com.tw.homework.View.ViewInterface;

/**
 * Created by Yasic on 2016/7/20.
 */
public abstract class BasePresenter<BVI extends ViewInterface> {
    protected BVI view;

    protected abstract Class<BVI> getBVIClass();
}
