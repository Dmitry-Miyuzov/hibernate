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
    @Key("servers")
    @Separator("@")
    String[] servers();
    @Key("port")
    Integer port();
    @Key("databaseName")
    String databaseName();
    @Key("username")
    String username();
    @Key("password")
    String password();
    @Key("driver.class")
    String driverClass();
    @Key("dialect")
    String dialect();
    @Key("showSql")
    Boolean showSql();
    @Key("formatSql")
    Boolean formatSql();
    @Key("useSqlComments")
    Boolean useSqlComments();

    @Key("hikari.minimumIdle")
    String minimumIdle();
    @Key("hikari.maximumPoolSize")
    String maximumPoolSize();
    @Key("hikari.connectionTimeout")
    String connectionTimeout();
    @Key("hikari.idleTimeout")
    String idleTimeout();
    @Key("hikari.maxLifetime")
    String maxLifetime();

    @Key("scan.entity.package")
    String scanEntityPackage();
}
