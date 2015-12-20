package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.PersonalityResponse;

@Repository("personalityResponseDao")
public class PersonalityResponseDaoImpl extends AbstractDao<Integer, PersonalityResponse> implements
    PersonalityResponseDao {

  public void saveResponse(PersonalityResponse response) {
    persist(response);
  }
}
