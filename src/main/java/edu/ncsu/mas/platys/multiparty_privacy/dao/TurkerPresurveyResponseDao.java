package edu.ncsu.mas.platys.multiparty_privacy.dao;

import edu.ncsu.mas.platys.multiparty_privacy.model.TurkerPresurveyResponse;

public interface TurkerPresurveyResponseDao {
  void saveResponse(TurkerPresurveyResponse response);
}
