package ru.test.ryazan.database.dao._base.sessionFactoryProvider;

import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import ru.test.ryazan.config._base.PersistenceUnitConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;
import java.util.function.Supplier;

@Slf4j
public class SessionFactoryProvider {
    public static Supplier<SessionFactory> getInstance(PersistenceUnitConfig config) {
        return () -> {
            log.info("Старт создания SessionFactory для БД -> %s".formatted(config.databaseName()));
            String server = tryGetSuccessServer(config);

            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.show_sql", config.showSql());
            configuration.setProperty("hibernate.format_sql", config.formatSql());
            configuration.setProperty("hibernate.use_sql_comments", config.useSqlComments());
            configuration.setProperty("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");

            configuration.setProperty("hibernate.hikari.poolName", config.poolName());
            configuration.setProperty("hibernate.hikari.autoCommit", "false");
            configuration.setProperty("hibernate.hikari.jdbcUrl", "jdbc:%s://%s:%s/%s".formatted(config.typeDatabaseJdbcUrl(), server, config.port(), config.databaseName()));
            configuration.setProperty("hibernate.hikari.dataSource.user", config.username());
            configuration.setProperty("hibernate.hikari.dataSource.password", config.password());


            //---------------------------- Более тяжелые параметры ----------------------------
            //Минимальное кол-во соединений в пуле.
            configuration.setProperty("hibernate.hikari.minimumIdle", config.minimumIdle());
            //Максимальное кол-во соединений в пуле.
            configuration.setProperty("hibernate.hikari.maximumPoolSize", config.maximumPoolSize());
            //По умолчанию 30000(30 секунд). Если не задавать. Но мы будем задавать в конфиге.
            configuration.setProperty("hibernate.hikari.connectionTimeout", config.connectionTimeout());

            /*
            Устанавливает максимальное кол-во времени, в течении которого соединение может простаивать в пуле.
            Применяется лишь в том случае - если minimumIdle меньше чем maximumPoolSize

            0 - означает что никогда не удаляются из пула.
            минимальное допустимое значение - 10000 (10 секунд)
            По умолчанию - 60000 (10 минут)
            Но мы будем задавать в конфиге.
            */
            configuration.setProperty("hibernate.hikari.idleTimeout", config.idleTimeout());

            /*
            Устанавливает максимальный срок службы соединения в пуле.
            Его рекомендуют установить и оно должно быть на несколько секунд меньше - чем ограничение времени соединения налагаемое БД.

            0 - указывает на отсутствие максимального срока службы. (но и зависит от настройки idleTimeout).
            минимальное допустимое значения - 30000 (30 секунд)
            По умолчанию - 1800000 (30 минут)
            */
            configuration.setProperty("hibernate.hikari.maxLifetime", config.maxLifetime());

            registerEntities(config, configuration);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            log.info("""
                    Создан Hikari Connection Pool -> %s.
                    minimumIdle: %s
                    maximumPoolSize: %s
                    connectionTimeout: %s
                    idleTimeout: %s
                    maxLifetime: %s
                    autoCommit: false
                    Чтобы получить дополнительную информацию, необходимо включить логи на уровень DEBUG.
                    """
                    .formatted(
                            config.poolName(),
                            config.minimumIdle(),
                            config.maximumPoolSize(),
                            config.connectionTimeout(),
                            config.idleTimeout(),
                            config.maxLifetime()
                    ));

            return sessionFactory;
        };
    }

    private static String tryGetSuccessServer(PersistenceUnitConfig config) {
        String successServer = null;

        String[] servers = config.servers();
        Integer port = config.port();
        String databaseName = config.databaseName();
        String username = config.username();
        String password = config.password();

        log.info("Попытка определить работающий server.");
        log.info("Кол-во servers - для попыток соединений: %d".formatted(servers.length));
        for (String server: servers) {
            log.info("Server -> %s".formatted(server));
            String jdbcUrl = "jdbc:%s://%s:%s/%s".formatted(config.typeDatabaseJdbcUrl(), server, port, databaseName);

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                successServer = server;
                log.info("Соединение успешно установлен.");
            } catch (Throwable e) {
                //лог
                log.info("Не удалось установить соединение.");
            }
        }

        if (successServer == null) {
            log.error("Не удалось установить ни одно соединение из перечисленных servers.");
            throw new RuntimeException("Не удалось установить ни одно соединение из перечисленных servers.");
        }

        return successServer;
    }

    private static void registerEntities(PersistenceUnitConfig config, Configuration configuration) {
        log.info("Регистрация сущностей для SessionFactory.");
        Reflections reflections = new Reflections(config.scanEntityPackage());
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        log.info("Поиск сущностей происходит в пакете: %s".formatted(config.scanEntityPackage()));
        log.info("Количество найденных сущностей: %d".formatted(entityClasses.size()));

        for (Class<?> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }
    }
}
