package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.PersonalityQuestion;

public interface PersonalityQuestionDao {
  PersonalityQuestion findById(int id);
  
  long getCount();

  void saveResponse(PersonalityQuestion question);
}
