package com.example.shirodemo.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long uid;
    private Long roleId;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        ", uid=" + uid +
        ", roleId=" + roleId +
        "}";
    }
}
