package com.tw.homework.View;

import com.tw.homework.Presenter.MainPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Yasic on 2016/7/18.
 */
public class MainView implements ViewInterface {

    private MainPresenter mainPresenter;

    public MainView(){
        mainPresenter = new MainPresenter();
    }

    public void sendString(final String input){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(input);
                subscriber.onCompleted();
            }
        })
                .observeOn(Schedulers.immediate())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(mainPresenter.transString(s));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                });
    }

    public static void main(String[] args) {
        final String barcodeInput = "[" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000001',\n" +
                "    'ITEM000003-2',\n" +
                "    'ITEM000005',\n" +
                "    'ITEM000005',\n" +
                "    'ITEM000005'\n" +
                "]";
        MainView mainView = new MainView();
        mainView.sendString(barcodeInput);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ViewInterface getView() {
        return this;
    }

    /*public void setPresenter(BasePresenter basePresenter) {
        this.mainPresenter = mainPresenter;
    }*/
}
