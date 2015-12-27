package edu.ncsu.mas.platys.crowdre.service;

import java.util.Map;
import java.util.Set;

public interface RequirementSelectorService {
  public Set<Integer> getUserIds();
  public Map<Integer, Double[]> getPersonalityTraits();
  Double[] getPersonalityTraits(int userId);
}
