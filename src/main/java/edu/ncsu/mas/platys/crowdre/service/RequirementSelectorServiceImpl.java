package edu.ncsu.mas.platys.crowdre.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.model.RequirementResponse;
import edu.ncsu.mas.platys.crowdre.util.CreativityComputer;
import edu.ncsu.mas.platys.crowdre.util.PersonalityComputer;

@Service("requirementSelectorService")
@Transactional
public class RequirementSelectorServiceImpl implements RequirementSelectorService {

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private Environment env;

  @Autowired
  RequirementResponseService requirementResponseService;

  private Integer studyPhase;
  
  private String selectionStrategy;
  
  // TODO Move this to Personality Computer
  private final Map<Integer, Double[]> otherUserIdToersonalityTraits = new HashMap<>();
  
  private CreativityComputer creativityComputer;

  @PostConstruct
  public void init() {
    
    studyPhase = Integer.parseInt(env.getProperty("study.phase"));
    selectionStrategy = env.getProperty("requirement.select.strategy");
    
    // TODO Not sure if this is correct
    Session session = sessionFactory.openSession();
    // Session session = sessionFactory.getCurrentSession(); // Didn't work

    if (selectionStrategy.endsWith("Personality")) {
      initOthersPeronsalityTraits(session);
    } else if (selectionStrategy.endsWith("Creativity")) {
      initOthersCreativityScores(session);
    }

    session.close();
  }

  private List<Integer> getOthersUserIds(Session session) {
    String queryStr = "select id as userId from users"
        + " where completion_code is not null and created_phase = " + (studyPhase - 1);

    SQLQuery query = session.createSQLQuery(queryStr).addScalar("userId");

    @SuppressWarnings("unchecked")
    List<Integer> users = (List<Integer>) query.list();
    return users;
  }

  private void initOthersPeronsalityTraits(Session session) {
    StringBuffer queryStrBuffer = new StringBuffer();
    queryStrBuffer.append("select user_id as userId, personality_question_id as pqId, "
        + "description as rawScore from personality_questions_users where user_id in (");

    List<Integer> userIds = getOthersUserIds(session);
    for (Integer userId : userIds) {
      queryStrBuffer.append(userId + ",");
    }
    queryStrBuffer.replace(queryStrBuffer.length() - 1, queryStrBuffer.length(), ")");

    SQLQuery query = session.createSQLQuery(queryStrBuffer.toString()).addScalar("userId")
        .addScalar("pqId").addScalar("rawScore");

    @SuppressWarnings("unchecked")
    List<Object[]> resultList = (List<Object[]>) query.list();
    otherUserIdToersonalityTraits.putAll(PersonalityComputer.computePersonalityTraits(resultList));
  }

  private void initOthersCreativityScores(Session session) {
    StringBuffer queryStrBuffer = new StringBuffer();
    queryStrBuffer.append("select user_id as userId, creativity_question_id as cqId, "
        + "description as rawScore from creativity_questions_users where user_id in (");

    List<Integer> userIds = getOthersUserIds(session);
    for (Integer userId : userIds) {
      queryStrBuffer.append(userId + ",");
    }
    queryStrBuffer.replace(queryStrBuffer.length() - 1, queryStrBuffer.length(), ")");

    SQLQuery query = session.createSQLQuery(queryStrBuffer.toString()).addScalar("userId")
        .addScalar("cqId").addScalar("rawScore");

    @SuppressWarnings("unchecked")
    List<Object[]> resultList = (List<Object[]>) query.list();
    creativityComputer = new CreativityComputer();
    creativityComputer.init(resultList);
  }
  
  @Override
  public List<RequirementResponse> getOthersRequirementsBasedOnPersonality(Double[] rawScores,
      String strategy, int size) {
    
    Double[] individualPersonalityTraits = PersonalityComputer
        .computePersonalityTraits(rawScores);


    TreeMap<Double, List<Integer>> sortedDistanceToUserIdList = PersonalityComputer
        .orderUserIdsByPersonalityDistance(individualPersonalityTraits, otherUserIdToersonalityTraits);

    List<Double> distances = new ArrayList<Double>();
    distances.addAll(sortedDistanceToUserIdList.keySet());

    List<RequirementResponse> requirementResponseList = new ArrayList<RequirementResponse>();

    if (strategy.equals("similarPersonality")) {
      for (int i = 0; i < distances.size(); i++) {
        List<Integer> userIdList = sortedDistanceToUserIdList.get(distances.get(i));
        for (Integer userId : userIdList) {
          requirementResponseList
              .addAll(requirementResponseService.findToShowOtherByUserId(userId));
        }
        if (requirementResponseList.size() >= 30) {
          break;
        }
      }
    } else if (strategy.equals("dissimilarPersonality")) {
      for (int i = distances.size() - 1; i >= 0; i--) {
        List<Integer> userIdList = sortedDistanceToUserIdList.get(distances.get(i));
        for (Integer userId : userIdList) {
          requirementResponseList
              .addAll(requirementResponseService.findToShowOtherByUserId(userId));
        }
        if (requirementResponseList.size() >= 30) {
          break;
        }
      }
    } else if (strategy.equals("mixedPersonality")) {
      for (int i = 0; i <= (distances.size() / 2); i++) {
        List<Integer> userIdList1 = sortedDistanceToUserIdList.get(distances.get(i));
        for (Integer userId : userIdList1) {
          requirementResponseList
              .addAll(requirementResponseService.findToShowOtherByUserId(userId));
        }
        List<Integer> userIdList2 = sortedDistanceToUserIdList.get(distances.get(distances.size()
            - i - 1));
        for (Integer userId : userIdList2) {
          requirementResponseList
              .addAll(requirementResponseService.findToShowOtherByUserId(userId));
        }
        if (requirementResponseList.size() >= 30) {
          break;
        }
      }
    } else { //randomPersonality
      Collections.shuffle(distances);
      for (int i = 0; i < distances.size(); i++) {
        List<Integer> userIdList = sortedDistanceToUserIdList.get(distances.get(i));
        for (Integer userId : userIdList) {
          requirementResponseList
              .addAll(requirementResponseService.findToShowOtherByUserId(userId));
        }
        if (requirementResponseList.size() >= 30) {
          break;
        }
      }
    }

    Collections.shuffle(requirementResponseList);

    List<RequirementResponse> outRequirementResponseList = new ArrayList<RequirementResponse>();
    for (RequirementResponse requirementResponse : requirementResponseList) {
      outRequirementResponseList.add(requirementResponse);
      if (outRequirementResponseList.size() >= size) {
        break;
      }
    }

    return outRequirementResponseList;
  }
  
  @Override
  public List<RequirementResponse> getOthersRequirementsBasedOnCreativity(Integer userId,
      Double[] rawScores, String strategy, int size) {
    
    List<Integer> otherUserIds = new ArrayList<>();
    otherUserIds.addAll(creativityComputer.getOthersUserIds(userId, rawScores, strategy));

    Collections.shuffle(otherUserIds);
    List<RequirementResponse> requirementResponseList = new ArrayList<RequirementResponse>();
    for (Integer otherUserId : otherUserIds) {
      requirementResponseList.addAll(requirementResponseService
          .findToShowOtherByUserId(otherUserId));
      if (requirementResponseList.size() >= 30) {
        break;
      }
    }
    
    Collections.shuffle(requirementResponseList);
    List<RequirementResponse> outRequirementResponseList = new ArrayList<RequirementResponse>();
    for (RequirementResponse requirementResponse : requirementResponseList) {
      outRequirementResponseList.add(requirementResponse);
      if (outRequirementResponseList.size() >= size) {
        break;
      }
    }

    return outRequirementResponseList;
  }
}
