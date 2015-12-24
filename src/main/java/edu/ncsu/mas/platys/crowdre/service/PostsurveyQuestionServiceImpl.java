package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PostsurveyQuestionDao;
import edu.ncsu.mas.platys.crowdre.model.PostsurveyQuestion;

@Service("postsurveyQuestionService")
@Transactional
public class PostsurveyQuestionServiceImpl implements PostsurveyQuestionService {

  @Autowired
  private PostsurveyQuestionDao dao;

  @Override
  public PostsurveyQuestion findById(int id) {
    return dao.findById(id);
  }
  
  @Override
  public long getCount() {
    return dao.getCount();
  }

  @Override
  public void saveResponse(PostsurveyQuestion question) {
    dao.saveResponse(question);
  }
}
