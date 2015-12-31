package edu.ncsu.mas.platys.crowdre.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
  private PersonalityComputer personalityComputer;
  
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
      
    } else if (selectionStrategy.endsWith("Combo")) {
      if (selectionStrategy.contains("Creativity")) {
        initOthersPeronsalityTraits(session);
      }
      if (selectionStrategy.contains("Creativity")) {
        initOthersCreativityScores(session);
      }
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
    personalityComputer = new PersonalityComputer();
    personalityComputer.init(resultList);

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

    List<Integer> otherUserIds = personalityComputer.getOthersUserIds(rawScores, strategy);
    
    return getRequirementResponses(otherUserIds, size);
  }
  
  @Override
  public List<RequirementResponse> getOthersRequirementsBasedOnCreativity(Integer userId,
      Double[] rawScores, String strategy, int size) {

    List<Integer> otherUserIds = new ArrayList<>();
    otherUserIds.addAll(creativityComputer.getOthersUserIds(userId, rawScores, strategy));

    Collections.shuffle(otherUserIds);

    return getRequirementResponses(otherUserIds, size);
  }
  
  @Override
  public List<RequirementResponse> getOthersRequirementsBasedOnPersonalityAndCreativity(
      Integer userId, Double[] rawScores, String strategy, int size) {
    
    List<Integer> otherUserIds = new ArrayList<>();
    // The order of the following two operations is important
    otherUserIds.addAll(personalityComputer.getOthersUserIds(rawScores, strategy));
    otherUserIds.retainAll(creativityComputer.getOthersUserIds(userId, rawScores, strategy));
    
    return getRequirementResponses(otherUserIds, size);
  }

  private List<RequirementResponse> getRequirementResponses(List<Integer> otherUserIds, int size) {
    
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
