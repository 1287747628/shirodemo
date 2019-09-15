package com.example.shirodemo.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Component
@ConfigurationProperties(prefix = "project")
@Validated
public class ProjectConstant {

    @NotEmpty
    private String code;
    private String authCheck;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthCheck() {
        return authCheck;
    }

    public void setAuthCheck(String authCheck) {
        this.authCheck = authCheck;
    }

    @Override
    public String toString() {
        return "ProjectConstant{" +
                "code='" + code + '\'' +
                ", authCheck='" + authCheck + '\'' +
                '}';
    }
}
