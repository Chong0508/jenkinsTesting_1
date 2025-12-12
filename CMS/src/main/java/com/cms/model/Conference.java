package com.cms.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "Conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConferenceID", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Acronym", nullable = false, length = 50)
    private String acronym;

    @Column(name = "`Year`", nullable = false)
    private Integer year;

    @Column(name = "Start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "End_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "Submission_deadline", nullable = false)
    private LocalDate submissionDeadline;

    @Column(name = "Notification_date", nullable = false)
    private LocalDate notificationDate;

    @Column(name = "PC_member_deadline", nullable = false)
    private LocalDate pcMemberDeadline;

    @Lob
    @Column(name = "Venue_details")
    private String venueDetails;

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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSubmissionDeadline() {
        return submissionDeadline;
    }

    public void setSubmissionDeadline(LocalDate submissionDeadline) {
        this.submissionDeadline = submissionDeadline;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDate notificationDate) {
        this.notificationDate = notificationDate;
    }

    public LocalDate getPcMemberDeadline() {
        return pcMemberDeadline;
    }

    public void setPcMemberDeadline(LocalDate pcMemberDeadline) {
        this.pcMemberDeadline = pcMemberDeadline;
    }

    public String getVenueDetails() {
        return venueDetails;
    }

    public void setVenueDetails(String venueDetails) {
        this.venueDetails = venueDetails;
    }

}