package ru.test.ryazan.config._base;

import org.aeonbits.owner.Config;

public interface PersistenceUnitConfig extends Config {
    String typeDatabaseJdbcUrl();
    String[] servers();
    Integer port();
    String databaseName();
    String username();
    String password();
    Boolean showSql();
    Boolean formatSql();
    Boolean useSqlComments();

    String poolName();
    String minimumIdle();
    String maximumPoolSize();
    String connectionTimeout();
    String idleTimeout();
    String maxLifetime();

    String scanEntityPackage();
}
