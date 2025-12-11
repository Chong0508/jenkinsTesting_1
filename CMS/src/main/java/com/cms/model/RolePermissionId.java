package com.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolePermissionId implements Serializable {
    private static final long serialVersionUID = -2432013599010361115L;
    @Column(name = "RoleID", nullable = false)
    private Long roleID;

    @Column(name = "PermissionID", nullable = false)
    private Long permissionID;

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Long getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Long permissionID) {
        this.permissionID = permissionID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolePermissionId entity = (RolePermissionId) o;
        return Objects.equals(this.permissionID, entity.permissionID) &&
                Objects.equals(this.roleID, entity.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionID, roleID);
    }

}