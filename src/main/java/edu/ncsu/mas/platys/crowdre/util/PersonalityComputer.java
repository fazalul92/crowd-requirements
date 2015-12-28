package edu.ncsu.mas.platys.crowdre.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


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

  public static Double[] computePersonalityTraits(Double[] personalityRawScores) {
    Double[] traits = new Double[5];

    if (personalityRawScores.length != 20) {
      throw new IllegalArgumentException("Length = " + personalityRawScores.length
          + ". There must be 20 scores from the mini IPIP test");
    }
    for (int i = 0; i < 20; i++) {
      if (personalityRawScores[i] < 1 || personalityRawScores[i] > 5) {
        throw new IllegalArgumentException("personalityScores[" + i + "] = " + personalityRawScores[i]
            + ". All scores must be in the 1 to 5 range");
      }
    }

    for (int i = 0; i < 5; i++) {
      traits[i] = (personalityRawScores[i] + (5 - personalityRawScores[i + 5])
          + personalityRawScores[i + 10] + (5 - personalityRawScores[i + 15])) / 4;
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

  public static TreeMap<Double, List<Integer>> orderUserIdsByPersonalityDistance(
      Double[] srcScores, Map<Integer, Double[]> destMap) {

    TreeMap<Double, List<Integer>> sortedDistances = new TreeMap<Double, List<Integer>>();

    for (Integer dest : destMap.keySet()) {
      Double distance = getPersonalityEuclideanDistance(srcScores, destMap.get(dest));

      List<Integer> userIds = sortedDistances.get(distance);
      if (userIds == null) {
        userIds = new ArrayList<Integer>();
        sortedDistances.put(distance, userIds);
      }
      userIds.add(dest);
    }

    return sortedDistances;
  }
}
