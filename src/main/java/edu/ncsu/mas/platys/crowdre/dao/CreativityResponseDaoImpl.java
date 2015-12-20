package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.CreativityResponse;

@Repository("creativityResponseDao")
public class CreativityResponseDaoImpl extends AbstractDao<Integer, CreativityResponse> implements
    CreativityResponseDao {

  public void saveResponse(CreativityResponse response) {
    persist(response);
  }
}
