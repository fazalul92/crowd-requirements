package edu.ncsu.mas.platys.crowdre.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.CreativityQuestion;

@Repository("creativityQuestionDao")
public class CreativityQuestionDaoImpl extends AbstractDao<Integer, CreativityQuestion> implements
    CreativityQuestionDao {

  public CreativityQuestion findById(int id) {
    return getByKey(id);
  }

  public long getCount() {
    Query query = getSession().createSQLQuery("select count(id) from creativity_questions");
    return ((BigInteger) query.uniqueResult()).longValue();
  }

  public void saveResponse(CreativityQuestion question) {
    persist(question);
  }
}
