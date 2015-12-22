package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.RequirementDao;
import edu.ncsu.mas.platys.crowdre.model.Requirement;

@Service("requirementService")
@Transactional
public class RequirementServiceImpl implements RequirementService {

  @Autowired
  private RequirementDao dao;

  @Override
  public void saveResponse(Requirement response) {
    dao.saveResponse(response);
  }

}
