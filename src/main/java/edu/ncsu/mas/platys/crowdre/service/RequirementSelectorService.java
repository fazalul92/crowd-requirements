package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;
import java.util.Map;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

public interface RequirementSelectorService {
  public Map<Integer, Double[]> getPersonalityTraits();

  public Double[] getPersonalityTraits(int userId);

  public List<RequirementResponse> getClosestRequirements(Double[] individualPersonalityTraits,
      boolean ascending, int size);

  public List<RequirementResponse> getClosestRequirementsFromRawScores(
      Double[] individualPersonalityRawScores, boolean ascending, int size);
}
