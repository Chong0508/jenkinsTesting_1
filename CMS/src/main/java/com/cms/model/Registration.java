package com.cms.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@Entity
@Table(name = "Registration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegistrationID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "UserID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ConferenceID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Conference conferenceID;

    @Column(name = "Registration_Type", nullable = false, length = 50)
    private String registrationType;

    @Column(name = "Registration_Date", nullable = false)
    private Instant registrationDate;

    @Column(name = "Attending_Status", nullable = false, length = 50)
    private String attendingStatus;

    @Column(name = "Meal_Preference", length = 50)
    private String mealPreference;

    @Lob
    @Column(name = "Special_Requests")
    private String specialRequests;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conference getConferenceID() {
        return conferenceID;
    }

    public void setConferenceID(Conference conferenceID) {
        this.conferenceID = conferenceID;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAttendingStatus() {
        return attendingStatus;
    }

    public void setAttendingStatus(String attendingStatus) {
        this.attendingStatus = attendingStatus;
    }

    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String mealPreference) {
        this.mealPreference = mealPreference;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

}