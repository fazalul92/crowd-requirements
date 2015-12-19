package edu.ncsu.mas.platys.crowdre.dao;

import edu.ncsu.mas.platys.crowdre.model.TurkerPresurveyResponse;

public interface TurkerPresurveyResponseDao {
  void saveResponse(TurkerPresurveyResponse response);
}
