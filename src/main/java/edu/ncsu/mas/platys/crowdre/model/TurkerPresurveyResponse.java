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
@Table(name = "turker_presurvey_response")
public class TurkerPresurveyResponse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Column(name = "mturk_id", nullable = false)
  private String mturkId;

  @NotNull
  @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "response_time", nullable = false)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime responseTime;

  @NotBlank
  @Column(name = "gender", nullable = false)
  private String gender;
  
  @NotBlank
  @Column(name = "age", nullable = false)
  private String age;

  @NotBlank
  @Column(name = "education", nullable = false)
  private String education;

  @NotBlank
  @Column(name = "socialmedia_frequency", nullable = false)
  private String socialmediaFrequency;
  
  @NotBlank
  @Column(name = "sharing_frequency", nullable = false)
  private String sharingFrequency;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMturkId() {
    return mturkId;
  }

  public void setMturkId(String mturkId) {
    this.mturkId = mturkId;
  }

  public LocalDateTime getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(LocalDateTime responseTime) {
    this.responseTime = responseTime;
  }
  
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getSocialmediaFrequency() {
    return socialmediaFrequency;
  }

  public void setSocialmediaFrequency(String socialmediaFrequency) {
    this.socialmediaFrequency = socialmediaFrequency;
  }

  public String getSharingFrequency() {
    return sharingFrequency;
  }

  public void setSharingFrequency(String sharingFrequency) {
    this.sharingFrequency = sharingFrequency;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof TurkerPresurveyResponse))
      return false;
    TurkerPresurveyResponse other = (TurkerPresurveyResponse) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TurkerPresurveyResponse [id=" + id + "]";
  }
}
