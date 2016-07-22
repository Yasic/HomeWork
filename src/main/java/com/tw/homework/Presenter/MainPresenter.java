package com.tw.homework.Presenter;

import com.tw.homework.JavaBean.FormatData;
import com.tw.homework.Model.OutputFormatModel;
import com.tw.homework.Model.TransformModel;
import com.tw.homework.View.MainView;

import java.util.TreeMap;

/**
 * Created by Yasic on 2016/7/20.
 */
public class MainPresenter extends BasePresenter<MainView>{
    private final TransformModel transformModel = new TransformModel();
    private final OutputFormatModel outputFormatModel = new OutputFormatModel.Builder().build();

    public String transString(final String input){
        TreeMap<String, FormatData> productFormatTreeMap = transformModel.transformToBarcodeAndFormatMap(transformModel.transformToBarcodeAndNumberMap(input));
        return outputFormatModel.getFormatOutput(productFormatTreeMap);
    }

    protected Class getBVIClass() {
        return MainView.class;
    }
}
