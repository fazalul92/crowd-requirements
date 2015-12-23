package edu.ncsu.mas.platys.crowdre.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
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
    Query query = getSession().createSQLQuery(
        "select description, application_domain, tags from requirements");
    @SuppressWarnings("unchecked")
    List<Object[]> results = query.list();
    
    RequirementResponse[] resuirementResponses = new RequirementResponse[results.size()];
    for (int i = 0; i < results.size(); i++) {
      resuirementResponses[i] = new RequirementResponse();
      resuirementResponses[i].setDescription((String) results.get(i)[0]);
      resuirementResponses[i].setApplicationDomain((String) results.get(i)[1]);
      resuirementResponses[i].setTags((String) results.get(i)[2]);
    }
    return resuirementResponses;
  }
  
  @Override
  public Map<String, List<RequirementResponse>> findByUserIdAndGroupByDomain(int userId) {
    Map<String, List<RequirementResponse>> requirementsMap = new LinkedHashMap<String, List<RequirementResponse>>();
    Query query = getSession().createSQLQuery(
        "select description, application_domain, tags from requirements");
    @SuppressWarnings("unchecked")
    List<Object[]> results = query.list();
    for (int i = 0; i < results.size(); i++) {
      RequirementResponse response = new RequirementResponse();
      response.setDescription((String) results.get(i)[0]);
      response.setApplicationDomain((String) results.get(i)[1]);
      response.setTags((String) results.get(i)[2]);
      
      List<RequirementResponse> responseList = requirementsMap.get(response.getApplicationDomain());
      if (responseList == null) {
        responseList = new ArrayList<RequirementResponse>();
        requirementsMap.put(response.getApplicationDomain(), responseList);
      }
      responseList.add(response);
    }
    return requirementsMap;
  }
}
