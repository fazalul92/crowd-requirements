package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

public interface RequirementResponseDao {
  RequirementResponse findById(int id);
  void saveResponse(RequirementResponse user);
}
