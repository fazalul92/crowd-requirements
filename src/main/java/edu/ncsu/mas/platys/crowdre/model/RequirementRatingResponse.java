package edu.ncsu.mas.platys.crowdre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "requirements_ratings")
public class RequirementRatingResponse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "requirement_id")
  private RequirementResponse requirementResponse;
  
  @NotNull
  @Column(name = "user_id", nullable = false)
  private int userId;

  @NotNull
  @Column(name = "novelty", nullable = false)
  private int novelty;

  @NotNull
  @Column(name = "feasibility", nullable = false)
  private int feasibility;

  @NotNull
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "created_at", nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public RequirementResponse getRequirementResponse() {
    return requirementResponse;
  }

  public void setRequirementResponse(RequirementResponse requirementResponse) {
    this.requirementResponse = requirementResponse;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getNovelty() {
    return novelty;
  }

  public void setNovelty(int novelty) {
    this.novelty = novelty;
  }

  public int getFeasibility() {
    return feasibility;
  }

  public void setFeasibility(int feasibility) {
    this.feasibility = feasibility;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
