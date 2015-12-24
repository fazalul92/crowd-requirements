package edu.ncsu.mas.platys.crowdre.dao;

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
  public void saveResponse(User user) {
    persist(user);
  }

}
