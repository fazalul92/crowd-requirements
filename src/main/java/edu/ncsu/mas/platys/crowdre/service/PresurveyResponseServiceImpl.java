package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PresurveyResponseDao;
import edu.ncsu.mas.platys.crowdre.model.PresurveyResponse;

@Service("turkerPresurveyResponseService")
@Transactional
public class PresurveyResponseServiceImpl implements PresurveyResponseService {

  @Autowired
  private PresurveyResponseDao dao;

  @Override
  public void saveResponse(PresurveyResponse response) {
    dao.saveResponse(response);
  }

}
