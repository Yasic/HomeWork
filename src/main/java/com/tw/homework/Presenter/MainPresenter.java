package com.tw.homework.Presenter;

import com.tw.homework.JavaBean.ProductFormat;
import com.tw.homework.Model.OutputModel;
import com.tw.homework.Model.TransformModel;
import com.tw.homework.View.MainView;

import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/20.
 */
public class MainPresenter extends BasePresenter<MainView>{
    private final TransformModel transformModel = new TransformModel();
    private final OutputModel outputModel = new OutputModel();

    public String transString(final String input){
        TreeMap<String, ProductFormat> productFormatTreeMap = transformModel.transformBarcodeAndNumberToBarcodeAndFormat(transformModel.transformInputToBarcodeAndNumber(input));
        return outputModel.getFormatOutput(productFormatTreeMap);
    }

    protected Class getBVIClass() {
        return MainView.class;
    }
}
