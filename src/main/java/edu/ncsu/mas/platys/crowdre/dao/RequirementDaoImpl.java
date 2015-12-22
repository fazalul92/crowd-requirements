package edu.ncsu.mas.platys.crowdre.dao;

import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.Requirement;

@Repository("requirementDao")
public class RequirementDaoImpl extends AbstractDao<Integer, Requirement> implements RequirementDao {

  public Requirement findById(int id) {
    return getByKey(id);
  }

  public void saveResponse(Requirement user) {
    persist(user);
  }

}
