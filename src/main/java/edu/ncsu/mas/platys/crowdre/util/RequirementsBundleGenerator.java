package edu.ncsu.mas.platys.crowdre.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException,
      Exception {

    try (RequirementsBundleGenerator bundleGen = new RequirementsBundleGenerator()) {
      // TODO
    }
  }
}
