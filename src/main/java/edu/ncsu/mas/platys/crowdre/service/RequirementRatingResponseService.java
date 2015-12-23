package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.RequirementRatingResponse;

public interface RequirementRatingResponseService {
  long getCount();
  void saveResponse(RequirementRatingResponse response);
}
