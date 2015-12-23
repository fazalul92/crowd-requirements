package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.RequirementRatingResponseDao;
import edu.ncsu.mas.platys.crowdre.model.RequirementRatingResponse;

@Service("requirementRatingResponseService")
@Transactional
public class RequirementRatingResponseServiceImpl implements RequirementRatingResponseService {

  @Autowired
  private RequirementRatingResponseDao dao;

  @Override
  public long getCount() {
    return dao.getCount();
  }

  @Override
  public void saveResponse(RequirementRatingResponse response) {
    dao.saveResponse(response);
  }
}
