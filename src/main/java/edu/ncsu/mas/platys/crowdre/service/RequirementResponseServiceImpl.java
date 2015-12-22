package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.RequirementResponseDao;
import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

@Service("requirementResponseService")
@Transactional
public class RequirementResponseServiceImpl implements RequirementResponseService {

  @Autowired
  private RequirementResponseDao dao;

  @Override
  public void saveResponse(RequirementResponse response) {
    dao.saveResponse(response);
  }

}
