package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;

public interface PresurveyQuestionDao {
  PresurveyQuestion findById(int id);
  
  long getCount();

  void saveResponse(PresurveyQuestion question);
}
