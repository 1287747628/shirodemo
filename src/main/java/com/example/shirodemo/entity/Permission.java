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
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Permission{" +
        ", id=" + id +
        ", name=" + name +
        ", description=" + description +
        "}";
    }
}
