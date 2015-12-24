package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PostsurveyResponseDao;
import edu.ncsu.mas.platys.crowdre.model.PostsurveyResponse;

@Service("postsurveyResponseService")
@Transactional
public class PostsurveyResponseServiceImpl implements PostsurveyResponseService {

  @Autowired
  private PostsurveyResponseDao dao;

  @Override
  public void saveResponse(PostsurveyResponse response) {
    dao.saveResponse(response);
  }

}
