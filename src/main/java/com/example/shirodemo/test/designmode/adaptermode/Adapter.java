package com.example.shirodemo.test.designmode.adaptermode;

/**
 * @author jocken
 * @date 2021/3/2
 */
public class Adapter implements Targetbable {

    private Source source;

    public Adapter(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method();
    }

    @Override
    public void method2() {
        System.out.println("adapter execute method");
    }
}
