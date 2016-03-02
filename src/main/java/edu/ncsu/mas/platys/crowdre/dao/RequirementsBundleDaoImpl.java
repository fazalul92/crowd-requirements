package edu.ncsu.mas.platys.crowdre.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.RequirementsBundle;

@Repository("requirementsBundleDao")
public class RequirementsBundleDaoImpl extends AbstractDao<Integer, RequirementsBundle> implements RequirementsBundleDao {

  @Override
  public RequirementsBundle findById(int id) {
    return getByKey(id);
  }

  @Override
  public long getCount() {
    Query query = getSession().createSQLQuery("select count(id) from scenario_bundle");
    return ((BigInteger) query.uniqueResult()).longValue();
  }
  
  @Override
  public List<Integer> getRequirementBundleIds(int completionTarget) {
    List<Integer> bundleIds = new ArrayList<Integer>();
    Query query = getSession().createSQLQuery("select id from requirements_bundles"
        + " where num_completed < :upperBound and num_completed >= :lowerBound");
    query.setInteger("lowerBound", completionTarget - 1);
    query.setInteger("upperBound", completionTarget);
    
    Iterator<?> itr = query.list().iterator();
    while (itr.hasNext()) {
      bundleIds.add((Integer) itr.next());
    }
    return bundleIds;
  }

  @Override
  public void incrementNumCompleted(int bundleId) {
    Query query = getSession().createSQLQuery(
        "update requirements_bundles set num_completed = num_completed + 1 where id = :id");
    query.setInteger("id", bundleId);
    query.executeUpdate();
  }

}
