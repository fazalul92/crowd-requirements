package edu.ncsu.mas.platys.crowdre.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;

@Repository("requirementResponseDao")
public class RequirementResponseDaoImpl extends AbstractDao<Integer, RequirementResponse> implements
    RequirementResponseDao {

  public RequirementResponse findById(int id) {
    return getByKey(id);
  }

  public void saveResponse(RequirementResponse user) {
    persist(user);
  }

  @Override
  public RequirementResponse[] findByUserId(int userId) {
    Criteria criteria = getSession().createCriteria(RequirementResponse.class);
    criteria.add(Restrictions.eq("userId", userId));
    
    @SuppressWarnings("unchecked")
    List<Object> responseList = criteria.list();
    
    RequirementResponse[] responseArray = new RequirementResponse[responseList.size()];
    for (int i = 0; i < responseArray.length; i++) {
      responseArray[i] = (RequirementResponse) responseList.get(i);
    }
    
    return responseArray;
  }

  @Override
  public Map<String, List<RequirementResponse>> findByUserIdAndGroupByDomain(int userId) {
    Map<String, List<RequirementResponse>> requirementsMap = new LinkedHashMap<String, List<RequirementResponse>>();

    Criteria criteria = getSession().createCriteria(RequirementResponse.class);
    criteria.add(Restrictions.eq("userId", userId));

    @SuppressWarnings("unchecked")
    List<Object> responseList = criteria.list();

    for (int i = 0; i < responseList.size(); i++) {
      RequirementResponse response = (RequirementResponse) responseList.get(i);
      List<RequirementResponse> domainResponseList = requirementsMap.get(response
          .getApplicationDomain());
      if (domainResponseList == null) {
        domainResponseList = new ArrayList<RequirementResponse>();
        requirementsMap.put(response.getApplicationDomain(), domainResponseList);
      }
      domainResponseList.add(response);
    }
    return requirementsMap;
  }
}
