package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;
import java.util.Map;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

public interface RequirementResponseService {
  RequirementResponse findById(int id);
  List<RequirementResponse> findByUserId(int userId);
  List<RequirementResponse> findToShowOtherByUserId(int userId);
  Map<String, List<RequirementResponse>> findByUserIdAndGroupByDomain(int userId);
  void saveResponse(RequirementResponse response);
}
