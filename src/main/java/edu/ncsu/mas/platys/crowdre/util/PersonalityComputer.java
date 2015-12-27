package edu.ncsu.mas.platys.crowdre.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

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

  public static SortedSet<Entry<Integer, Double>> orderPersonalityScoresByDistance(
      Double[] srcScores, Map<Integer, Double[]> destMap, boolean ascending) {
    Map<Integer, Double> distances = new HashMap<Integer, Double>();
    for (Integer dest : destMap.keySet()) {
      distances.put(dest, getPersonalityEuclideanDistance(srcScores, destMap.get(dest)));
    }

    SortedSet<Entry<Integer, Double>> sortedDistances;
    if (ascending) {
      sortedDistances = entriesSortedByValuesAsc(distances);
    } else {
      sortedDistances = entriesSortedByValuesDsc(distances);
    }
    return sortedDistances;
  }

  private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValuesAsc(
      Map<K, V> map) {
    SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
        new Comparator<Map.Entry<K, V>>() {
          @Override
          public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
            int res = e1.getValue().compareTo(e2.getValue());
            return res != 0 ? res : 1;
          }
        });
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
  }

  private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValuesDsc(
      Map<K, V> map) {
    SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
        new Comparator<Map.Entry<K, V>>() {
          @Override
          public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
            int res = e2.getValue().compareTo(e1.getValue());
            return res != 0 ? res : 1;
          }
        });
    sortedEntries.addAll(map.entrySet());
    return sortedEntries;
  }

}
