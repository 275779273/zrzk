package com.zrzk.pojo;

import java.util.Set;

public class TRole {
    private Integer id;

    private String name;

    private String description;

    /**
     * 角色对应权限集合
     */
    private Set<TPermission> permissions;

    public TRole(Integer id, String name, Set<TPermission> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

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

    public Set<TPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<TPermission> permissions) {
        this.permissions = permissions;
    }
}