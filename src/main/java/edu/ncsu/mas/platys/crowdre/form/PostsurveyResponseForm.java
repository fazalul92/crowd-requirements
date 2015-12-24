package edu.ncsu.mas.platys.crowdre.form;

import edu.ncsu.mas.platys.crowdre.model.PostsurveyResponse;

public class PostsurveyResponseForm {
  private PostsurveyResponse[] presurveyResponses;

  public PostsurveyResponse[] getPresurveyResponses() {
    return presurveyResponses;
  }

  public void setPostsurveyResponses(PostsurveyResponse[] presurveyResponses) {
    this.presurveyResponses = presurveyResponses;
  }
}
