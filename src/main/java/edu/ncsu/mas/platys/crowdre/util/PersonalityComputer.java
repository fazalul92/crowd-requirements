package edu.ncsu.mas.platys.crowdre.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalityComputer {
  /**
   * Reads personality scores
   * 
   * @param resultSet
   *          Each row must be userId (Integer), questionId (Integer), score
   *          (String)
   */
  public static Map<Integer, Double[]> computePersonalityTraits(List<Object[]> resultSet) {
    Map<Integer, Double[]> personalityRawScores = readPersonalityRawScores(resultSet);
    
    Map<Integer, Double[]> personalityTraits = new HashMap<Integer, Double[]>();
    for (Integer userId : personalityRawScores.keySet()) {
      personalityTraits.put(userId, computePersonalityTraits(personalityRawScores.get(userId)));
    }
    
    return personalityTraits;
  }

  public static Map<Integer, Double[]> readPersonalityRawScores(List<Object[]> resultSet) {
    Map<Integer, Double[]> personalityScores = new HashMap<Integer, Double[]>();
    for (Object[] result : resultSet) {
      Integer userId = (Integer) result[0];
      Integer questionId = (Integer) result[1];
      Integer score = Integer.parseInt((String) result[2]);

      Double[] userScores = personalityScores.get(userId);
      if (userScores == null) {
        userScores = new Double[20];
        personalityScores.put(userId, userScores);
      }
      userScores[questionId - 1] = score.doubleValue();
    }
    return personalityScores;
  }

  public static Double[] computePersonalityTraits(Double[] personalityScores) {
    Double[] traits = new Double[5];

    if (personalityScores.length != 20) {
      throw new IllegalArgumentException("Length = " + personalityScores.length
          + ". There must be 20 scores from the mini IPIP test");
    }
    for (int i = 0; i < 20; i++) {
      if (personalityScores[i] < 1 || personalityScores[i] > 5) {
        throw new IllegalArgumentException("personalityScores[" + i + "] = " + personalityScores[i]
            + ". All scores must be in the 1 to 5 range");
      }
    }

    for (int i = 0; i < 5; i++) {
      traits[i] = (personalityScores[i] + (5 - personalityScores[i + 5])
          + personalityScores[i + 10] + (5 - personalityScores[i + 15])) / 4;
    }

    return traits;
  }

  public static Double getPersonalityEuclideanDistance(Double[] trait1, Double[] trait2) {
    Double squaredDistance = 0.0;
    for (int i = 0; i < 5; i++) {
      squaredDistance += (trait1[i] - trait2[i]) * (trait1[i] - trait2[i]);
    }
    return Math.sqrt(squaredDistance);
  }
}
