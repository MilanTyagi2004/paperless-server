package com.paperless.model;

import com.paperless.model.baseModal.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class UserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email_id")
    private String emailId;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "password_reset_date")
    private LocalDateTime passwordResetDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    private boolean isEmailConfirm;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "active")
    private boolean active;

    @Column(name = "last_login_date")
    private LocalDateTime last_login_date;

    private Long profilePictureFileId;

    private String profilePictureUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }



    public LocalDateTime getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(LocalDateTime last_login_date) {
        this.last_login_date = last_login_date;
    }

    public LocalDateTime getPasswordResetDate() {
        return passwordResetDate;
    }

    public void setPasswordResetDate(LocalDateTime passwordResetDate) {
        this.passwordResetDate = passwordResetDate;
    }

    public boolean isEmailConfirm() {
        return isEmailConfirm;
    }

    public void setEmailConfirm(boolean emailConfirm) {
        isEmailConfirm = emailConfirm;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getProfilePictureFileId() {
        return profilePictureFileId;
    }

    public void setProfilePictureFileId(Long profilePictureFileId) {
        this.profilePictureFileId = profilePictureFileId;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public UserInfo() {
    }
}
