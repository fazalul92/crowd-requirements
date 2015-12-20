package edu.ncsu.mas.platys.crowdre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.dao.PersonalityResponseDao;
import edu.ncsu.mas.platys.crowdre.model.PersonalityResponse;

@Service("personalityResponseService")
@Transactional
public class PersonalityResponseServiceImpl implements PersonalityResponseService {

  @Autowired
  private PersonalityResponseDao dao;

  @Override
  public void saveResponse(PersonalityResponse response) {
    dao.saveResponse(response);
  }

}
