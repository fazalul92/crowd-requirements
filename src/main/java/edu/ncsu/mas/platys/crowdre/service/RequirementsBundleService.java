package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;

import edu.ncsu.mas.platys.crowdre.model.RequirementsBundle;

public interface RequirementsBundleService {
  RequirementsBundle findById(int id);
  long getCount();
  List<Integer> getRequirementBundleIds(int completionCountTarget);
  void incrementNumCompleted(int bundleId);
}
