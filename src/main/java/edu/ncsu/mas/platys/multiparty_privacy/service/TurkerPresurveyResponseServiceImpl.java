package edu.ncsu.mas.platys.multiparty_privacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.multiparty_privacy.dao.TurkerPresurveyResponseDao;
import edu.ncsu.mas.platys.multiparty_privacy.model.TurkerPresurveyResponse;

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
