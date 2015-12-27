package edu.ncsu.mas.platys.crowdre.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.mas.platys.crowdre.util.PersonalityComputer;

@Service("requirementSelectorService")
@Transactional
public class RequirementSelectorServiceImpl implements RequirementSelectorService {

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private Environment env;

  private final Set<Integer> userIds = new HashSet<Integer>();

  private final Map<Integer, Double[]> personalityTraits = new HashMap<Integer, Double[]>();

  private Integer studyPhase;

  @PostConstruct
  public void init() {
    studyPhase = Integer.parseInt(env.getProperty("study.phase"));

    Session session = sessionFactory.openSession(); // TODO Not sure if this is
                                                    // correct
    // Session session = sessionFactory.getCurrentSession();

    initUsers(session);

    initPeronsalityTraits(session);

    session.close();
  }

  private void initUsers(Session session) {
    String queryStr = "select id as userId from users"
        + " where completion_code is not null and created_phase = " + studyPhase.toString();

    SQLQuery query = session.createSQLQuery(queryStr).addScalar("userId");

    @SuppressWarnings("unchecked")
    List<Integer> resultList = (List<Integer>) query.list();
    userIds.addAll(resultList);
  }

  private void initPeronsalityTraits(Session session) {
    StringBuffer queryStrBuffer = new StringBuffer();
    queryStrBuffer.append("select user_id as userId, personality_question_id as pqId, "
        + "description as rawScore from personality_questions_users where user_id in (");

    for (Integer userId : userIds) {
      queryStrBuffer.append(userId + ",");
    }
    queryStrBuffer.replace(queryStrBuffer.length() - 1, queryStrBuffer.length(), ")");

    SQLQuery query = session.createSQLQuery(queryStrBuffer.toString()).addScalar("userId")
        .addScalar("pqId").addScalar("rawScore");

    @SuppressWarnings("unchecked")
    List<Object[]> resultList = (List<Object[]>) query.list();
    personalityTraits.putAll(PersonalityComputer.computePersonalityTraits(resultList));
  }

  @Override
  public Set<Integer> getUserIds() {
    return userIds;
  }

  @Override
  public Map<Integer, Double[]> getPersonalityTraits() {
    return personalityTraits;
  }

  @Override
  public Double[] getPersonalityTraits(int userId) {
    return personalityTraits.get(userId);
  }
}
