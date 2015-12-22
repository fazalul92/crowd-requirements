package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

@Repository("requirementResponseDao")
public class RequirementResponseDaoImpl extends AbstractDao<Integer, RequirementResponse> implements
    RequirementResponseDao {

  public RequirementResponse findById(int id) {
    return getByKey(id);
  }

  public void saveResponse(RequirementResponse user) {
    persist(user);
  }

}
