package com.example.shirodemo.test.designmode.factorymode.staticfactory;

/**
 * @author jocken
 * @date 2020/10/15
 */
public class StaticFactoryTest {

    public static void main(String[] args) {
        Provider mailFactory = new SendMailFactory();
        Send mailSend = mailFactory.produce();
        mailSend.send();
    }

}
