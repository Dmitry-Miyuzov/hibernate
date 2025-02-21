package ru.test.ryazan.config.impl;

import org.aeonbits.owner.Config;
import ru.test.ryazan.config._base.PersistenceUnitConfig;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:config/${env}/database/tasks.properties"
})
public interface TasksPersistenceUnitConfig extends PersistenceUnitConfig {
    @Key("db.tasks.typeDatabaseJdbcUrl")
    String typeDatabaseJdbcUrl();
    @Key("db.tasks.servers")
    @Separator("@")
    String[] servers();
    @Key("db.tasks.port")
    Integer port();
    @Key("db.tasks.databaseName")
    String databaseName();
    @Key("db.tasks.username")
    String username();
    @Key("db.tasks.password")
    String password();
    @Key("db.tasks.showSql")
    Boolean showSql();
    @Key("db.tasks.formatSql")
    Boolean formatSql();
    @Key("db.tasks.useSqlComments")
    Boolean useSqlComments();

    @Key("db.tasks.hikari.poolName")
    String poolName();
    @Key("db.tasks.hikari.minimumIdle")
    String minimumIdle();
    @Key("db.tasks.hikari.maximumPoolSize")
    String maximumPoolSize();
    @Key("db.tasks.hikari.connectionTimeout")
    String connectionTimeout();
    @Key("db.tasks.hikari.idleTimeout")
    String idleTimeout();
    @Key("db.tasks.hikari.maxLifetime")
    String maxLifetime();

    @Key("db.tasks.scan.entity.package")
    String scanEntityPackage();
}
