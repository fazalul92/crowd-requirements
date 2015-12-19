package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.TurkerPresurveyResponse;

@Repository("turkerPresurveyResponseDao")
public class TurkerPresurveyResponseDaoImpl extends AbstractDao<Integer, TurkerPresurveyResponse>
    implements TurkerPresurveyResponseDao {

  public void saveResponse(TurkerPresurveyResponse response) {
    persist(response);
  }

}
