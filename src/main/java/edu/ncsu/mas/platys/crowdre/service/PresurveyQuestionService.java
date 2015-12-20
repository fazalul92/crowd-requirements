package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;

public interface PresurveyQuestionService {
  PresurveyQuestion findById(int id);
  long getCount();
  void saveResponse(PresurveyQuestion question);
}
