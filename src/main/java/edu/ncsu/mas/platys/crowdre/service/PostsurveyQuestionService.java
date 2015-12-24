package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.PostsurveyQuestion;

public interface PostsurveyQuestionService {
  PostsurveyQuestion findById(int id);
  long getCount();
  void saveResponse(PostsurveyQuestion question);
}
