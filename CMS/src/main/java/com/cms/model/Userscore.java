package com.cms.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "UserScore")
public class Userscore {

    @EmbeddedId
    private UserscoreId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User userID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BadgeID", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Badge badgeID;

    @Column(name = "`value`", nullable = false)
    private int value;

    public UserscoreId getId() {
        return id;
    }

    public void setId(UserscoreId id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Badge getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(Badge badgeID) {
        this.badgeID = badgeID;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}