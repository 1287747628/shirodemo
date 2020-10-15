package com.example.shirodemo.test.designmode.factorymode.staticfactory;

/**
 * @author jocken
 * @date 2020/10/15
 */
public class SmsSend implements Send {

    @Override
    public void send() {
        System.out.println("sms send");
    }

}
