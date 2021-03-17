package com.example.shirodemo.vo;

import com.example.shirodemo.constants.StatusCode;

public class Response {

    private Integer resultCode;
    private String description;

    public static Response build(int resultCode, String description) {
        Response resp = new Response();
        resp.setResultCode(resultCode);
        resp.setDescription(description);
        return resp;
    }

    public static Response buildFailed(String description) {
        Response resp = new Response();
        resp.setResultCode(StatusCode.UI._UI_1);
        resp.setDescription(description);
        return resp;
    }

    public static Response buildSuccess(String description) {
        Response resp = new Response();
        resp.setResultCode(StatusCode.UI.UI_0);
        resp.setDescription(description);
        return resp;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
