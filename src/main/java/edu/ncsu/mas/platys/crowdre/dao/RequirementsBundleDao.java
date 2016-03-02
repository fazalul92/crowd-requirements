package edu.ncsu.mas.platys.crowdre.dao;

import java.util.List;

import edu.ncsu.mas.platys.crowdre.model.RequirementsBundle;

public interface RequirementsBundleDao {
  RequirementsBundle findById(int id);
  long getCount();
  List<Integer> getRequirementBundleIds(int completionCountTarget);
  void incrementNumCompleted(int bundleId);
}
