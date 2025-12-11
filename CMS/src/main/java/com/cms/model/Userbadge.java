package com.cms.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserBadge")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Userbadge {
    @EmbeddedId
    private UserbadgeId id;

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "UserID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User userID;

    @MapsId("badgeID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "BadgeID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Badge badgeID;

    @Column(name = "Date_Earned", nullable = false)
    private LocalDateTime dateEarned;

    public UserbadgeId getId() {
        return id;
    }

    public void setId(UserbadgeId id) {
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

    public LocalDateTime getDateEarned() {
        return dateEarned;
    }

    public void setDateEarned(LocalDateTime dateEarned) {
        this.dateEarned = dateEarned;
    }

}