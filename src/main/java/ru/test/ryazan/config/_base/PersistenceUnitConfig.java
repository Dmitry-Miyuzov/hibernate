package ru.test.ryazan.config._base;

import org.aeonbits.owner.Config;

public interface PersistenceUnitConfig extends Config {
    String[] servers();
    Integer port();
    String databaseName();
    String username();
    String password();
    String driverClass();
    String dialect();
    Boolean showSql();
    Boolean formatSql();
    Boolean useSqlComments();

    String minimumIdle();
    String maximumPoolSize();
    String connectionTimeout();
    String idleTimeout();
    String maxLifetime();

    String scanEntityPackage();
}
