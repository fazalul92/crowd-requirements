package edu.ncsu.mas.platys.crowdre.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class RequirementsBundleGenerator implements AutoCloseable {

  private final Properties mProps = new Properties();

  private final Connection mConn;

  public RequirementsBundleGenerator() throws ClassNotFoundException, SQLException, IOException {
    try (InputStream inStream = RequirementsBundleGenerator.class
        .getResourceAsStream("/application.properties")) {

      mProps.load(inStream);
      Class.forName(mProps.getProperty("jdbc.driverClassName"));

      mConn = DriverManager.getConnection(mProps.getProperty("jdbc.url") + "?user="
          + mProps.getProperty("jdbc.username") + "&password="
          + mProps.getProperty("jdbc.password"));
    }
  }

  @Override
  public void close() throws Exception {
    mConn.close();
  }
  
  private Map<String, List<Integer>> getRequirementIds() throws SQLException {
    Map<String, List<Integer>> reqDomainToIds = new HashMap<String, List<Integer>>();
    String tagsSelectQuery = "select requirements.id, application_domain"
        + " from requirements, users"
        + " where show_other != 0 and completion_code is not null"
        + "   and requirements.user_id = users.id"
        + " order by requirements.id";
    
    try (Statement stmt = mConn.createStatement();
        ResultSet rs = stmt.executeQuery(tagsSelectQuery)) {
      while (rs.next()) {
        List<Integer> reqIds = reqDomainToIds.get(rs.getString(2));
        if (reqIds == null) {
          reqIds = new ArrayList<Integer>();
          reqDomainToIds.put(rs.getString(2), reqIds);
        }
        reqIds.add(rs.getInt(1));
      }
    }
    return reqDomainToIds;
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException,
      Exception {

    try (RequirementsBundleGenerator bundleGen = new RequirementsBundleGenerator()) {
      int domainId = 0;
      List<Integer> domainIds = new ArrayList<Integer>();
      List<String> domainNames = new ArrayList<String>();
      
      Random domainRand = new Random();
      Random reqIdRand = new Random();
      
      Map<String, List<Integer>> reqDomainToIds = bundleGen.getRequirementIds();
      Map<String, List<Integer>> leftOverDomainToIds = new HashMap<String, List<Integer>>();
      
      for (String domainName : reqDomainToIds.keySet()) {
        System.out.println(domainName + ": " + reqDomainToIds.get(domainName).size());
        domainNames.add(domainName);
        for (int i = 0; i < reqDomainToIds.get(domainName).size(); i++) {
          domainIds.add(domainId);
        }
        domainId++;
      }

      while(reqDomainToIds.size() >= 3) {
        List<Integer> curIds = new ArrayList<Integer>();
        while (curIds.size() < 3) {
          Integer nextRandId = domainRand.nextInt(domainIds.size());
          Integer nextDomainId = domainIds.get(nextRandId);
          if (!curIds.contains(nextDomainId)) {
            curIds.add(nextDomainId);
            // domainIds.remove(nextDomainId);
          }
        }
        for (Integer curId : curIds) {
          String domainName = domainNames.get(curId);
          List<Integer> domainReqIds = reqDomainToIds.get(domainName);
          if (domainReqIds.size() < 10) {
            throw new IllegalStateException("Size of domainReIds less than ten");
          }
          List<Integer> curReqIds = new ArrayList<Integer>();
          while (curReqIds.size() < 10) {
            Integer nextReqId = domainReqIds.get(reqIdRand.nextInt(domainReqIds.size()));
            curReqIds.add(nextReqId);
            domainReqIds.remove(nextReqId);
          }
          
          if (domainReqIds.size() < 10) {
            leftOverDomainToIds.put(domainName, domainReqIds);
            reqDomainToIds.remove(domainName);
            domainIds.removeAll(Collections.singleton(domainNames.indexOf(domainName)));
          }
          System.out.print(domainName + ": " + curReqIds.size() + "; ");
        }
        System.out.println();
      }
     
      System.out.println("Left overs 1");
      for (String domainName : reqDomainToIds.keySet()) {
        System.out.println(domainName + ": " + reqDomainToIds.get(domainName).size());
      }
      
      System.out.println("Left overs 2");
      for (String domainName : leftOverDomainToIds.keySet()) {
        System.out.println(domainName + ": " + leftOverDomainToIds.get(domainName).size());
      }
    }
  }
}
