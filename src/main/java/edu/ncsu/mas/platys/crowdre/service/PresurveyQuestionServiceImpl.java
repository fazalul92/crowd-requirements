package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PresurveyQuestionDao;
import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;

@Service("turkerPresurveyResponseService")
@Transactional
public class PresurveyQuestionServiceImpl implements PresurveyQuestionService {

  @Autowired
  private PresurveyQuestionDao dao;

  @Override
  public PresurveyQuestion findById(int id) {
    return dao.findById(id);
  }
  
  @Override
  public long getCount() {
    return dao.getCount();
  }

  @Override
  public void saveResponse(PresurveyQuestion question) {
    dao.saveResponse(question);
  }
}
