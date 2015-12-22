package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.Requirement;

public interface RequirementDao {
  Requirement findById(int id);
  void saveResponse(Requirement user);
}
