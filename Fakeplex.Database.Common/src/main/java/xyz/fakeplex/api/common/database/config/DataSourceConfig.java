package xyz.fakeplex.api.common.database.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

/**
 * @author Kyle
 */
@Slf4j
@Configuration
public class DataSourceConfig {

  private final String _configFile = "database-config.dat";

  private DataSource ACCOUNT;

  /** Loads DataSources from the configuration file */
  private void loadDataSources() {
    try {
      File configFile = new File(_configFile);

      if (configFile.exists()) {
        List<String> lines = Files.readAllLines(configFile.toPath(), Charset.defaultCharset());

        for (String line : lines) deserializeConnection(line);

        return;
      }

      log.error("Could not find database configuration file : " + _configFile);
    } catch (Exception exception) {
      exception.printStackTrace();
      log.error("Could not parse database configuration file : " + _configFile);
    }
  }

  /**
   * Deserializes the specified string into a DataSource object
   *
   * @param line The string to deserialize
   */
  private void deserializeConnection(String line) {
    String[] args = line.split(" ");

    if (args.length == 4) {
      String dbSource = args[0];
      String dbHost = args[1];
      String userName = args[2];
      String password = args[3];

      log.info(
          "DataSource("
              + _configFile
              + ") : "
              + dbSource
              + " "
              + dbHost
              + " "
              + userName
              + " "
              + "*".repeat(password.length()));

      if (dbSource.equalsIgnoreCase("ACCOUNT"))
        ACCOUNT = openDataSource("jdbc:mysql://" + dbHost, userName, password);
    }
  }

  /**
   * Creates a DataSource object using the specified url, username, and password
   *
   * @param url The URL of the DataSource
   * @param username The username used to access the DataSource
   * @param password The password used to access the DataSource
   * @return A DataSource object that can be used to establish a connection to the data source
   */
  private DataSource openDataSource(String url, String username, String password) {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(url);
    dataSourceBuilder.username(username);
    dataSourceBuilder.password(password);
    return dataSourceBuilder.build();
  }

  /**
   * Returns a DataSource object that represents the account database If the account information
   * isn't configured, this method returns null
   *
   * @return A DataSource object that provides access to the account database
   */
  public DataSource getAccount() {
    if (ACCOUNT == null) loadDataSources();

    return ACCOUNT;
  }

  /**
   * Provides instances of EntityManager for connecting to the database
   *
   * @return LocalContainerEntityManagerFactoryBean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(getDataSource());
    em.setPackagesToScan("xyz.fakeplex.api");
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(getHibernateProperties());
    return em;
  }

  /**
   * Provides Hibernate properties
   *
   * @return Hibernate properties.
   */
  private Properties getHibernateProperties() {
    Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty(
        "hibernate.physical_naming_strategy",
        "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
    return hibernateProperties;
  }

  /**
   * Returns the main Database as a DataSource Returns null if the database couldn't be connected to
   *
   * @return Account DataSource
   */
  @Bean
  public DataSource getDataSource() {
    DataSource connectionFactory = getAccount();

    if (connectionFactory == null) {
      log.error("Could not find 'ACCOUNT' in " + _configFile);
      throw new RuntimeException("Invalid configuration file: " + _configFile);
    }

    return connectionFactory;
  }
}
