package ru.test.ryazan.sessionFactoryProvider;

import jakarta.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;
import ru.test.ryazan.config._base.PersistenceUnitConfig;
import ru.test.ryazan.dao.tasks.schema.tasks.entity.DictionaryEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;
import java.util.function.Supplier;

public class ServiceRegistryProvider {
    public static Supplier<SessionFactory> getInstance(PersistenceUnitConfig config) {
        return () -> {
            String server = tryGetSuccessServer(config);

            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://%s:%s/%s".formatted(server, config.port(), config.databaseName()));
            configuration.setProperty("hibernate.connection.username", config.username());
            configuration.setProperty("hibernate.connection.password", config.password());
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

            configuration.setProperty("hibernate.show_sql", String.valueOf(config.showSql()));
            configuration.setProperty("hibernate.format_sql", String.valueOf(config.formatSql()));
            configuration.setProperty("hibernate.use_sql_comments", String.valueOf(config.useSqlComments()));




//        //---------------------------- Более тяжелые параметры ----------------------------
//        //Минимальное кол-во соединений в пуле.
//        configuration.setProperty("hibernate.hikari.minimumIdle", config.minimumIdle());
//        //Максимальное кол-во соединений в пуле.
//        configuration.setProperty("hibernate.hikari.maximumPoolSize", config.maximumPoolSize());
//        //По умолчанию 30000(30 секунд). Если не задавать. Но мы будем задавать в конфиге.
//        configuration.setProperty("hibernate.hikari.connectionTimeout", config.connectionTimeout());
//
//        /*
//        Устанавливает максимальное кол-во времени, в течении которого соединение может простаивать в пуле.
//        Применяется лишь в том случае - если minimumIdle меньше чем maximumPoolSize
//
//        0 - означает что никогда не удаляются из пула.
//        минимальное допустимое значение - 10000 (10 секунд)
//        По умолчанию - 60000 (10 минут)
//        Но мы будем задавать в конфиге.
//         */
//        configuration.setProperty("hibernate.hikari.idleTimeout", config.idleTimeout());
//
//        /*
//        Устанавливает максимальный срок службы соединения в пуле.
//        Его рекомендуют установить и оно должно быть на несколько секунд меньше - чем ограничение времени соединения налагаемое БД.
//
//        0 - указывает на отсутствие максимального срока службы. (но и зависит от настройки idleTimeout).
//        минимальное допустимое значения - 30000 (30 секунд)
//        По умолчанию - 1800000 (30 минут)
//         */
//        configuration.setProperty("hibernate.hikari.maxLifetime", config.maxLifetime());

        Reflections reflections = new Reflections(config.scanEntityPackage());
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);

        for (Class<?> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }


            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            return configuration.buildSessionFactory(serviceRegistry);
        };
    }

    private static String tryGetSuccessServer(PersistenceUnitConfig config) {
        String successServer = null;

        String[] servers = config.servers();
        Integer port = config.port();
        String databaseName = config.databaseName();
        String username = config.username();
        String password = config.password();

        for (String server: servers) {
            String jdbcUrl = "jdbc:postgresql://%s:%s/%s".formatted(server, port, databaseName);

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                successServer = server;
            } catch (Throwable e) {
                //лог
                System.out.println();
            }
        }

        if (successServer == null) {
            throw new RuntimeException("Описать внятное");
        }

        return successServer;
    }
}
