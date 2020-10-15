package com.example.shirodemo.test.designmode.factorymode.staticfactory;

/**
 * @author jocken
 * @date 2020/10/15
 */
public class SendMailFactory implements Provider {

    @Override
    public Send produce() {
        return new MailSend();
    }

}
