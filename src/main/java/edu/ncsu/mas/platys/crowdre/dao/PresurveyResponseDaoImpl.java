package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.PresurveyResponse;

@Repository("presurveyResponseDao")
public class PresurveyResponseDaoImpl extends AbstractDao<Integer, PresurveyResponse> implements
    PresurveyResponseDao {

  public void saveResponse(PresurveyResponse response) {
    persist(response);
  }
}
