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
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long permissionId;
    private Long roleId;


    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        ", permissionId=" + permissionId +
        ", roleId=" + roleId +
        "}";
    }
}
