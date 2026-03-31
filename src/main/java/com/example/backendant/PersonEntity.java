package com.example.backendant;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "persons")
@Entity
public class PersonEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String personName;
    @Column(name = "person_password")
    private String personPassword;
    @Column(name = "person_status")
    @Enumerated(EnumType.STRING)
    private PersonStatus personStatus;
    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    public PersonEntity() {
    }

    public PersonEntity(
            Long id, String personName, String personPassword, PersonStatus personStatus, LocalDateTime entryTime
    ) {
        this.id = id;
        this.personName = personName;
        this.personPassword = personPassword;
        this.personStatus = personStatus;
        this.entryTime = entryTime;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public PersonStatus getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(PersonStatus personStatus) {
        this.personStatus = personStatus;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
