package com.zrzk.rms.pojo;

import java.util.List;

public class TRole {
    private Integer id;

    private String name;

    private String description;

    /**
     * 角色对应权限集合
     */
    private List<TPermission> permissions;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "TRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}