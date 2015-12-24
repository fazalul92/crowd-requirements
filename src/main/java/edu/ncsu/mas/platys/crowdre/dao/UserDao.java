package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.User;

public interface UserDao {
  User findById(int id);
  void updateResponse(User user);
  void saveResponse(User user);
}
