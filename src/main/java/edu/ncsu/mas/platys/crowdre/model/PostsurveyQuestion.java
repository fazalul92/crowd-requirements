package edu.ncsu.mas.platys.crowdre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "postsurvey_questions")
public class PostsurveyQuestion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Column(name = "description", nullable = false)
  private String description;

  @NotBlank
  @Column(name = "question_type")
  private String questionType;

  @NotBlank
  @Column(name = "answer_choices")
  private String answerChoices;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getQuestionType() {
    return questionType;
  }

  public void setQuestionType(String questionType) {
    this.questionType = questionType;
  }

  public String getAnswerChoices() {
    return answerChoices;
  }

  public void setAnswerChoices(String answerChoices) {
    this.answerChoices = answerChoices;
  }
}
