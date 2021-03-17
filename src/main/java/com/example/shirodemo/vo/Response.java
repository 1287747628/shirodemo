package com.example.shirodemo.vo;

import com.example.shirodemo.constants.StatusCode;

public class Response {

    private Integer resultCode;
    private String description;

    public Response(int resultCode) {
        this.resultCode = resultCode;
        if (resultCode == StatusCode.UI.UI_0) {
            this.setDescription("SUCCESS");
        }
        if (resultCode == StatusCode.UI._UI_1) {
            this.setDescription("FAIL");
        }
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
