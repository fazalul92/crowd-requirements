package edu.ncsu.mas.platys.multiparty_privacy.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.multiparty_privacy.model.TurkerPresurveyResponse;

@Repository("turkerPresurveyResponseDao")
public class TurkerPresurveyResponseDaoImpl extends AbstractDao<Integer, TurkerPresurveyResponse>
    implements TurkerPresurveyResponseDao {

  public void saveResponse(TurkerPresurveyResponse response) {
    persist(response);
  }

}
