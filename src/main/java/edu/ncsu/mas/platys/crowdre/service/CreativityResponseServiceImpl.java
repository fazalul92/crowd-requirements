package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.CreativityResponseDao;
import edu.ncsu.mas.platys.crowdre.model.CreativityResponse;

@Service("creativityResponseService")
@Transactional
public class CreativityResponseServiceImpl implements CreativityResponseService {

  @Autowired
  private CreativityResponseDao dao;

  @Override
  public void saveResponse(CreativityResponse response) {
    dao.saveResponse(response);
  }

}
