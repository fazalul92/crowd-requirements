package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;

public interface CreativityQuestionDao {
  CreativityQuestion findById(int id);
  
  long getCount();

  void saveResponse(CreativityQuestion question);
}
