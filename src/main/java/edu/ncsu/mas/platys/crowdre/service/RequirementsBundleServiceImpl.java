package edu.ncsu.mas.platys.crowdre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.RequirementsBundleDao;
import edu.ncsu.mas.platys.crowdre.model.RequirementsBundle;

@Service("requirementsBundleService")
@Transactional
public class RequirementsBundleServiceImpl implements RequirementsBundleService {

  @Autowired
  private RequirementsBundleDao dao;

  @Override
  public RequirementsBundle findById(int id) {
    return dao.findById(id);
  }
    
  @Override
  public long getCount() {
    return dao.getCount();
  }
  
  @Override
  public List<Integer> getRequirementBundleIds(int completionTarget) {
    return dao.getRequirementBundleIds(completionTarget);
  }
  
  @Override
  public void incrementNumCompleted(int bundleId) {
    dao.incrementNumCompleted(bundleId);
  }
}
