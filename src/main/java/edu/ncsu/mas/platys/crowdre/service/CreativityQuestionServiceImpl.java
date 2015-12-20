package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.CreativityQuestionDao;
import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;

@Service("creativityQuestionService")
@Transactional
public class CreativityQuestionServiceImpl implements CreativityQuestionService {

  @Autowired
  private CreativityQuestionDao dao;

  @Override
  public CreativityQuestion findById(int id) {
    return dao.findById(id);
  }
  
  @Override
  public long getCount() {
    return dao.getCount();
  }

  @Override
  public void saveResponse(CreativityQuestion question) {
    dao.saveResponse(question);
  }
}
