package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.TurkerPresurveyResponseDao;
import edu.ncsu.mas.platys.crowdre.model.TurkerPresurveyResponse;

@Service("turkerPresurveyResponseService")
@Transactional
public class TurkerPresurveyResponseServiceImpl implements TurkerPresurveyResponseService {

  @Autowired
  private TurkerPresurveyResponseDao dao;

  @Override
  public void saveResponse(TurkerPresurveyResponse response) {
    dao.saveResponse(response);
  }

}
