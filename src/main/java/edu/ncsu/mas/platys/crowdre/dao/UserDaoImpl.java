package edu.ncsu.mas.platys.crowdre.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import edu.ncsu.mas.platys.crowdre.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

  @Override
  public User findById(int id) {
    return getByKey(id);
  }

  @Override
  public void updateResponse(User user) {
    update(user);
  }
  
  @Override
  public int getResponseCount(int userId) {
    Criteria criteria = getSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("userId", userId)).add(Restrictions.isNotNull("completionCode"))
        .setProjection(Projections.rowCount());
    return ((Number) criteria.uniqueResult()).intValue();
  }
  
  @Override
  public int getResponseCount(String mturkId) {
    Criteria criteria = getSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("mturkId", mturkId)).add(Restrictions.isNotNull("completionCode"))
        .setProjection(Projections.rowCount());
    return ((Number) criteria.uniqueResult()).intValue();
  }

  @Override
  public void saveResponse(User user) {
    persist(user);
  }

}
