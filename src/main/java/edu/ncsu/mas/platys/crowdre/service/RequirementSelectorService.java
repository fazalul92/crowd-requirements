package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

public interface RequirementSelectorService {
  public List<RequirementResponse> getOthersRequirementsBasedOnPersonality(Double[] rawScores,
      String selectionStrategy, int size);

  public List<RequirementResponse> getOthersRequirementsBasedOnCreativity(Integer userId,
      Double[] rawScores, String selectionStrategy, int size);

}
