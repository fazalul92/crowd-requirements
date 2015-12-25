package edu.ncsu.mas.platys.crowdre.service;

import edu.ncsu.mas.platys.crowdre.model.User;

public interface UserService {
  User findById(int id);
  int getResponseCount(int userId);
  int getResponseCount(String mturkId);
  void updateResponse(User response);
  void saveResponse(User response);
}
