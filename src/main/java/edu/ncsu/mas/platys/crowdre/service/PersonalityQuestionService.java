package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.PersonalityQuestion;

public interface PersonalityQuestionService {
  PersonalityQuestion findById(int id);
  long getCount();
  void saveResponse(PersonalityQuestion question);
}
