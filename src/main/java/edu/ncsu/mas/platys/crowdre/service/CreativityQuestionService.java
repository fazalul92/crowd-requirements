package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;

public interface CreativityQuestionService {
  CreativityQuestion findById(int id);
  long getCount();
  void saveResponse(CreativityQuestion question);
}
