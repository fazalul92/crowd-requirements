package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.RequirementRatingResponse;

public interface RequirementRatingResponseDao {
  RequirementRatingResponse findById(int id);
  long getCount();
  void saveResponse(RequirementRatingResponse user);
}
