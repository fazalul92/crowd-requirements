package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;
import java.util.Map;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

public interface RequirementSelectorService {
  public Map<Integer, Double[]> getPersonalityTraits();

  public Double[] getPersonalityTraits(int userId);

  public List<RequirementResponse> getOthersRequirementsFromRawScores(Double[] rawScores, int size);
}
