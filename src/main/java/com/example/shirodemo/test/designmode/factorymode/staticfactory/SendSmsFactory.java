package com.example.shirodemo.test.designmode.factorymode.staticfactory;

/**
 * @author jocken
 * @date 2020/10/15
 */
public class SendSmsFactory implements Provider {

    @Override
    public Send produce() {
        return new SmsSend();
    }
}
