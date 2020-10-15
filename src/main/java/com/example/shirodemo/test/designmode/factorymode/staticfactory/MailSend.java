package com.example.shirodemo.test.designmode.factorymode.staticfactory;

/**
 * @author jocken
 * @date 2020/10/15
 */
public class MailSend implements Send {

    @Override
    public void send() {
        System.out.println("mail send");
    }
}
