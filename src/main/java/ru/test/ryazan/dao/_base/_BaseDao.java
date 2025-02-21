package ru.test.ryazan.dao._base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.test.ryazan.config._base.PersistenceUnitConfig;
import ru.test.ryazan.sessionFactoryProvider.ServiceRegistryProvider;

import java.util.HashMap;
import java.util.Map;

public abstract class _BaseDao {
    private static final Map<String, SessionFactory> storageSessionFactories = new HashMap<>();
    public SessionFactory sessionFactory;

    protected _BaseDao(PersistenceUnitConfig config) {
        sessionFactory = getSessionFactory(config);
    }

    private synchronized SessionFactory getSessionFactory(PersistenceUnitConfig config) {
        if (storageSessionFactories.get(config.databaseName()) != null) {
            return storageSessionFactories.get(config.databaseName());
        } else {
            SessionFactory sessionFactory = ServiceRegistryProvider.getInstance(config).get();

            storageSessionFactories.put(config.databaseName(), sessionFactory);
            return sessionFactory;
        }
    }
}
