package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PersonalityQuestionDao;
import edu.ncsu.mas.platys.crowdre.model.PersonalityQuestion;

@Service("personalityQuestionService")
@Transactional
public class PersonalityQuestionServiceImpl implements PersonalityQuestionService {

  @Autowired
  private PersonalityQuestionDao dao;

  @Override
  public PersonalityQuestion findById(int id) {
    return dao.findById(id);
  }
  
  @Override
  public long getCount() {
    return dao.getCount();
  }

  @Override
  public void saveResponse(PersonalityQuestion question) {
    dao.saveResponse(question);
  }
}
