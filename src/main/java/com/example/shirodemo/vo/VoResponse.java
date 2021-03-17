package com.example.shirodemo.vo;

/**
 * @author jocken
 * @date 2021/3/17
 */
public class VoResponse<T> extends Response {

    private T data;

    public VoResponse(int resultCode, String desc, T t) {
        this.setResultCode(resultCode);
        this.setDescription(desc);
        this.setData(t);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
