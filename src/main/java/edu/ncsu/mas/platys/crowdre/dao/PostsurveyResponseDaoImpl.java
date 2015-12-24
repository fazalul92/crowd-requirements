package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.PostsurveyResponse;

@Repository("postsurveyResponseDao")
public class PostsurveyResponseDaoImpl extends AbstractDao<Integer, PostsurveyResponse> implements
    PostsurveyResponseDao {

  public void saveResponse(PostsurveyResponse response) {
    persist(response);
  }
}
