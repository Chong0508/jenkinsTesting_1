package com.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserbadgeId implements Serializable {
    private static final long serialVersionUID = -2050729005378923209L;
    @Column(name = "UserID", nullable = false)
    private Long userID;

    @Column(name = "BadgeID", nullable = false)
    private Long badgeID;

    public UserbadgeId() {} // no-arg constructor required by JPA

    public UserbadgeId(Long userID, Long badgeID) {
        this.userID = userID;
        this.badgeID = badgeID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(Long badgeID) {
        this.badgeID = badgeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserbadgeId entity = (UserbadgeId) o;
        return Objects.equals(this.badgeID, entity.badgeID) &&
                Objects.equals(this.userID, entity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeID, userID);
    }

}