package edu.ncsu.mas.platys.crowdre.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.RequirementRatingResponse;

@Repository("requirementRatingResponseDao")
public class RequirementRatingResponseDaoImpl extends
    AbstractDao<Integer, RequirementRatingResponse> implements RequirementRatingResponseDao {

  public RequirementRatingResponse findById(int id) {
    return getByKey(id);
  }

  public long getCount() {
    Query query = getSession().createSQLQuery("select count(id) from requirements_ratings");
    return ((BigInteger) query.uniqueResult()).longValue();
  }

  public void saveResponse(RequirementRatingResponse user) {
    persist(user);
  }
}
