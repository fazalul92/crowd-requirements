package edu.ncsu.mas.platys.crowdre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "requirements")
public class RequirementResponse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(name = "user_id", nullable = false)
  private int userId;

  @NotBlank
  @Column(name = "role", nullable = false)
  private String role;
  
  @NotBlank
  @Column(name = "feature", nullable = false)
  private String feature;

  @NotBlank
  @Column(name = "benefit", nullable = false)
  private String benefit;

  @NotBlank
  @Column(name = "application_domain", nullable = false)
  private String applicationDomain;

  @Column(name = "application_domain_other")
  private String applicationDomainOther;

  @Column(name = "tags")
  private String tags;

  @NotNull
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "created_at", nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime createdAt;

  @Column(name = "show_other")
  private int showOther;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getBenefit() {
    return benefit;
  }

  public void setBenefit(String benefit) {
    this.benefit = benefit;
  }

  public String getFeature() {
    return feature;
  }

  public void setFeature(String feature) {
    this.feature = feature;
  }

  public String getApplicationDomain() {
    return applicationDomain;
  }

  public void setApplicationDomain(String applicationDomain) {
    this.applicationDomain = applicationDomain;
  }

  public String getApplicationDomainOther() {
    return applicationDomainOther;
  }

  public void setApplicationDomainOther(String applicationDomainOther) {
    this.applicationDomainOther = applicationDomainOther;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public int getShowOther() {
    return showOther;
  }

  public void setShowOther(int showOther) {
    this.showOther = showOther;
  }
}
