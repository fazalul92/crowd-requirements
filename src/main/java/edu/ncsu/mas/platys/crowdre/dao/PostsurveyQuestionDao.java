package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.PostsurveyQuestion;

public interface PostsurveyQuestionDao {
  PostsurveyQuestion findById(int id);
  
  long getCount();

  void saveResponse(PostsurveyQuestion question);
}
