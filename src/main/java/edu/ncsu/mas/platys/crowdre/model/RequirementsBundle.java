package edu.ncsu.mas.platys.crowdre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "requirements_bundles")
public class RequirementsBundle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Column(name = "application_domain_1", nullable = false)
  private String applicationDomain1;
  
  @NotBlank
  @Column(name = "req_ids_1", nullable = false)
  private String requirementIds1;

  @NotBlank
  @Column(name = "application_domain_2", nullable = false)
  private String applicationDomain2;
  
  @NotBlank
  @Column(name = "req_ids_2", nullable = false)
  private String requirementIds2;

  @NotBlank
  @Column(name = "application_domain_3", nullable = false)
  private String applicationDomain3;
  
  @NotBlank
  @Column(name = "req_ids_3", nullable = false)
  private String requirementIds3;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getApplicationDomain1() {
    return applicationDomain1;
  }

  public void setApplicationDomain1(String applicationDomain1) {
    this.applicationDomain1 = applicationDomain1;
  }

  public String getRequirementIds1() {
    return requirementIds1;
  }

  public void setRequirementIds1(String requirementIds1) {
    this.requirementIds1 = requirementIds1;
  }

  public String getApplicationDomain2() {
    return applicationDomain2;
  }

  public void setApplicationDomain2(String applicationDomain2) {
    this.applicationDomain2 = applicationDomain2;
  }

  public String getRequirementIds2() {
    return requirementIds2;
  }

  public void setRequirementIds2(String requirementIds2) {
    this.requirementIds2 = requirementIds2;
  }

  public String getApplicationDomain3() {
    return applicationDomain3;
  }

  public void setApplicationDomain3(String applicationDomain3) {
    this.applicationDomain3 = applicationDomain3;
  }

  public String getRequirementIds3() {
    return requirementIds3;
  }

  public void setRequirementIds3(String requirementIds3) {
    this.requirementIds3 = requirementIds3;
  }
}
