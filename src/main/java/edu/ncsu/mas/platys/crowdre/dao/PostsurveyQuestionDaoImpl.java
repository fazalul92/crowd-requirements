package edu.ncsu.mas.platys.crowdre.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.PostsurveyQuestion;

@Repository("postsurveyQuestionDao")
public class PostsurveyQuestionDaoImpl extends AbstractDao<Integer, PostsurveyQuestion> implements
    PostsurveyQuestionDao {

  public PostsurveyQuestion findById(int id) {
    return getByKey(id);
  }

  public long getCount() {
    Query query = getSession().createSQLQuery("select count(id) from postsurvey_questions");
    return ((BigInteger) query.uniqueResult()).longValue();
  }

  public void saveResponse(PostsurveyQuestion question) {
    persist(question);
  }
}
