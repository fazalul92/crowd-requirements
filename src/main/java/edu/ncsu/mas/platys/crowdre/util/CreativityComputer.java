package edu.ncsu.mas.platys.crowdre.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CreativityComputer {
  
  private final boolean[] positiveAttributes = new boolean[30];
  private final String positiveAttributeStr = "0,2,4,5,7,9,11,13,16,18,19,20,22,24,25,26,28,29";

  private final Map<Integer, Double> lowCreativityBucket = new HashMap<>();
  private Double lowCreativityBucketMean;

  private final Map<Integer, Double> mediumCreativityBucket = new HashMap<>();
  private Double mediumCreativityBucketMean;

  private final Map<Integer, Double> highCreativityBucket = new HashMap<>();
  private Double highCreativityBucketMean;

  /**
   * Initialization; must be called before other methods
   * 
   * @param resultList
   *          Each row must be userId (Integer), questionId (Integer), score
   *          (String)
   */
  public void init(List<Object[]> resultList) {
    String[] positiveIndices = positiveAttributeStr.split(",");
    for (String positiveIndex : positiveIndices) {
      positiveAttributes[Integer.parseInt(positiveIndex)] = true;
    }

    Map<Integer, Double> creativityScores = computeCreativityScores(resultList);
    partitionCreativityScores(creativityScores);
  }

  public Set<Integer> getOthersUserIds(Integer userId, Double[] rawScores, String strategy) {
    Double userCreativityScore = computeCreativityScore(rawScores);
    String userBucket = findBucket(userCreativityScore);
    
    if (strategy.contains("similarCreativity")) {
      if (userBucket.equals("low")) {
        return lowCreativityBucket.keySet();
        
      } else if (userBucket.equals("medium")) {
        return mediumCreativityBucket.keySet();
        
      } else {
        return highCreativityBucket.keySet();
      }
    } else if (strategy.contains("higherCreativity")) {
      if (userBucket.equals("low")) {
        return mediumCreativityBucket.keySet();
        
      } else if (userBucket.equals("medium")) {
        return highCreativityBucket.keySet();
        
      } else {
        System.out.println("Strategy = " + strategy + "; userId = " + userId);
        return highCreativityBucket.keySet();
      }      
    } else if (strategy.contains("lowerCreativity")) {
      if (userBucket.equals("low")) {
        System.out.println("Strategy = " + strategy + "; userId = " + userId);
        return lowCreativityBucket.keySet();
        
      } else if (userBucket.equals("medium")) {
        return lowCreativityBucket.keySet();
        
      } else {
        return mediumCreativityBucket.keySet();
      }            
    }
    
    throw new IllegalArgumentException(strategy + " is an illegal creativity strategy");
  }

  private Map<Integer, Double> computeCreativityScores(List<Object[]> resultList) {
    Map<Integer, Double[]> creativityRawScores = readCreativityRawScores(resultList);

    Map<Integer, Double> creativityScores = new HashMap<Integer, Double>();
    for (Integer userId : creativityRawScores.keySet()) {
      creativityScores.put(userId, computeCreativityScore(creativityRawScores.get(userId)));
    }

    return creativityScores;
  }

  private void partitionCreativityScores(Map<Integer, Double> userIdTocreativityScore) {
    TreeMap<Double, List<Integer>> creativityScoreToUserId = new TreeMap<>();
    for (Integer userId : userIdTocreativityScore.keySet()) {
      Double creativityScore = userIdTocreativityScore.get(userId);
      List<Integer> userIdList = creativityScoreToUserId.get(creativityScore);
      if (userIdList == null) {
        userIdList = new ArrayList<>();
        creativityScoreToUserId.put(creativityScore, userIdList);
      }
      userIdList.add(userId);
    }

    List<Double> creativityScoreList = new ArrayList<>();
    creativityScoreList.addAll(creativityScoreToUserId.keySet());

    int firstQuarrtileIndex = creativityScoreList.size() / 3;
    int secondQuartileIndex = firstQuarrtileIndex * 2;

    for (int i = 0; i < firstQuarrtileIndex; i++) {
      Double creativityScore = creativityScoreList.get(i);
      for (Integer userId : creativityScoreToUserId.get(creativityScore)) {
        lowCreativityBucket.put(userId, creativityScore);
      }
    }
    lowCreativityBucketMean = findMeanValue(lowCreativityBucket.values());

    for (int i = firstQuarrtileIndex; i < secondQuartileIndex; i++) {
      Double creativityScore = creativityScoreList.get(i);
      for (Integer userId : creativityScoreToUserId.get(creativityScore)) {
        mediumCreativityBucket.put(userId, creativityScore);
      }
    }
    mediumCreativityBucketMean = findMeanValue(mediumCreativityBucket.values());

    for (int i = secondQuartileIndex; i < creativityScoreList.size(); i++) {
      Double creativityScore = creativityScoreList.get(i);
      for (Integer userId : creativityScoreToUserId.get(creativityScore)) {
        highCreativityBucket.put(userId, creativityScore);
      }
    }
    highCreativityBucketMean = findMeanValue(highCreativityBucket.values());
  }

  private Map<Integer, Double[]> readCreativityRawScores(List<Object[]> resultSet) {
    Map<Integer, Double[]> creativityRawScores = new HashMap<Integer, Double[]>();
    for (Object[] result : resultSet) {
      Integer userId = (Integer) result[0];
      Integer questionId = (Integer) result[1];
      Integer score = Integer.parseInt((String) result[2]);

      Double[] userScores = creativityRawScores.get(userId);
      if (userScores == null) {
        userScores = new Double[30];
        creativityRawScores.put(userId, userScores);
      }
      userScores[questionId - 1] = score.doubleValue();
    }
    return creativityRawScores;
  }

  private Double computeCreativityScore(Double[] rawScores) {
    Double creativityScore = 0.0;

    if (rawScores.length != 30) {
      throw new IllegalArgumentException("Length = " + rawScores.length
          + ". There must be 30 scores from the mini IPIP test");
    }
    for (int i = 0; i < 30; i++) {
      if (rawScores[i] < 1 || rawScores[i] > 5) {
        throw new IllegalArgumentException("creativityScores[" + i + "] = " + rawScores[i]
            + ". All scores must be in the 1 to 5 range");
      }
    }

    for (int i = 0; i < 30; i++) {
      creativityScore += positiveAttributes[i] ? rawScores[i] : (5 - rawScores[i]);
    }

    return (creativityScore / 30);
  }

  private static Double findMeanValue(Collection<Double> values) {
    Double valueSum = 0.0;
    for (Double value : values) {
      valueSum += value;
    }
    return (valueSum / values.size());
  }

  private String findBucket(Double creativityScore) {
    Double distanceToLowBucket = Math.abs(creativityScore - lowCreativityBucketMean);
    Double distanceToMediumBucket = Math.abs(creativityScore - mediumCreativityBucketMean);
    Double distanceToHighBucket = Math.abs(creativityScore - highCreativityBucketMean);
    
    String bucket = "low";
    
    if (distanceToMediumBucket <= distanceToLowBucket) {
      bucket = "medium";
    } 
    
    if (distanceToHighBucket <= distanceToMediumBucket) {
      bucket = "high";
    }
    
    return bucket;
  }
}
