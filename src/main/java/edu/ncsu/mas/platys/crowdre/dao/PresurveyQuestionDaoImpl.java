package edu.ncsu.mas.platys.crowdre.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.PresurveyQuestion;

@Repository("presurveyQuestionDao")
public class PresurveyQuestionDaoImpl extends AbstractDao<Integer, PresurveyQuestion> implements
    PresurveyQuestionDao {

  public PresurveyQuestion findById(int id) {
    return getByKey(id);
  }

  public long getCount() {
    Query query = getSession().createSQLQuery("select count(id) from presurvey_questions");
    return ((BigInteger) query.uniqueResult()).longValue();
  }

  public void saveResponse(PresurveyQuestion question) {
    persist(question);
  }
}
