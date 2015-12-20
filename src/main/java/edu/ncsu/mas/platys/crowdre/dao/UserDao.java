package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.User;

public interface UserDao {
  void saveResponse(User user);
}
