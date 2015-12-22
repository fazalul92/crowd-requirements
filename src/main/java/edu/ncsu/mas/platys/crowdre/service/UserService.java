package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.User;

public interface UserService {
  User findById(int id);
  void saveResponse(User response);
}
