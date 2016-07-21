package com.tw.homework.View;

import com.tw.homework.Presenter.MainPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Yasic on 2016/7/20.
 */
public class UserView extends JFrame implements ViewInterface<MainPresenter> {
    private JTextField jTargetIPField;
    private JLabel jGateWayText;
    private JTextField jGateWayField;
    private JLabel jLocalMacText;
    private JTextField jLocalMacField;
    private JLabel jTargetMacText;
    private JTextField jTargetMacField;
    private JButton jLocalNetworkAttackButton;
    private JButton jSingleTargetAttackButton;
    private JButton jCancelAttackButton;
    private JButton jExitButton;
    private Thread thread;
    private JLabel jTipText;

    private JLabel inputText;
    private JButton jButton;
    private JTextArea inputCase;
    private JLabel outputText;
    private JTextArea outputCase;
    private MainPresenter mainPresenter;
    private JTextField promotionInfo;


    private UserView(){
        super();
        this.setSize(1000, 600);
        this.getContentPane().setLayout(null);
        this.add(getJTargetInputCase());
        this.add(getJTargetInputText());
        this.add(getSendButton());
        this.add(getOutPutText());
        this.add(getOutputCase());
        this.add(getPromotionList());
        this.setTitle("没钱赚商店的收银机");
        mainPresenter = new MainPresenter();

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(inputCase.getText());
                        subscriber.onCompleted();
                    }
                })
                        .observeOn(Schedulers.immediate())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                outputCase.setText(mainPresenter.transString(s));
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                outputCase.setText(throwable.getMessage());
                            }
                        });
            }
        });
    }

    private javax.swing.JLabel getjTipText() {
        if (jTipText == null) {
            jTipText = new javax.swing.JLabel();
            jTipText.setBounds(20, 235, 190, 60);
            jTipText.setText("等待指令");
        }
        return jTipText;
    }

    private JLabel getJTargetInputText() {
        if (inputText == null) {
            inputText = new JLabel();
            inputText.setBounds(20, 10, 300, 40);
            Font font=new Font("楷体", Font.ROMAN_BASELINE,18);
            inputText.setFont(font);
            inputText.setText("输入示例：");
        }
        return inputText;
    }

    private JTextArea getJTargetInputCase() {
        if (inputCase == null) {
            inputCase = new JTextArea();
            inputCase.setBounds(20, 60, 420, 350);
            Font font=new Font("宋体", Font.ROMAN_BASELINE,21);
            inputCase.setFont(font);
            inputCase.setText("[\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000001',\n" +
                    "    'ITEM000003-2',\n" +
                    "    'ITEM000005',\n" +
                    "    'ITEM000005',\n" +
                    "    'ITEM000005'\n" +
                    "]");
        }
        return inputCase;
    }

    private javax.swing.JButton getSendButton() {
        if(jButton == null) {
            jButton = new javax.swing.JButton();
            jButton.setBounds(20, 500, 940, 50);
            jButton.setText("输入");
        }
        return jButton;
    }

    private JLabel getOutPutText(){
        if (outputText == null){
            outputText = new JLabel();
            outputText.setBounds(500, 10, 300, 40);
            Font font=new Font("楷体", Font.ROMAN_BASELINE,18);
            outputText.setFont(font);
            outputText.setText("输出结果：");
        }
        return outputText;
    }

    private JTextArea getOutputCase() {
        if (outputCase == null) {
            outputCase = new JTextArea();
            outputCase.setBounds(500, 60, 420, 350);
            Font font=new Font("宋体", Font.ROMAN_BASELINE,18);
            outputCase.setFont(font);
            outputCase.setLineWrap(true);
            outputCase.setText("");
        }
        return outputCase;
    }

    private JTextField getPromotionList(){
        if (promotionInfo == null){
            promotionInfo = new JTextField();
            promotionInfo.setBounds(20, 450, 940, 50);
            Font font=new Font("宋体", Font.ROMAN_BASELINE,18);
            promotionInfo.setText("买二赠一：可口可乐，羽毛球" + "\t" + "九五折优惠：羽毛球，可口可乐");

            promotionInfo.setFont(font);
            promotionInfo.setEditable(false);
        }
        return promotionInfo;
    }

    public static void main(String[] args)
    {
        UserView w = new UserView();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);

    }

    @Override
    public ViewInterface getView() {
        return this;
    }
}
