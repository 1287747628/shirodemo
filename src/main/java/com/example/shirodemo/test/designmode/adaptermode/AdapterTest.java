package com.example.shirodemo.test.designmode.adaptermode;

/**
 * @author jocken
 * @date 2021/3/2
 */
public class AdapterTest {

    public static void main(String[] args) {
        Source source = new Source();
        Targetbable target = new Adapter(source);
        target.method1();
        target.method2();
    }

}
