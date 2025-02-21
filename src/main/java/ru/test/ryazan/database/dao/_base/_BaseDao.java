package ru.test.ryazan.database.dao._base;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.test.ryazan.config._base.PersistenceUnitConfig;
import ru.test.ryazan.database.dao._base.sessionFactoryProvider.SessionFactoryProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public abstract class _BaseDao {
    private static final Map<String, SessionFactory> storageSessionFactories = new HashMap<>();
    protected SessionFactory sessionFactory;

    protected _BaseDao(PersistenceUnitConfig config) {
        sessionFactory = getSessionFactory(config);
    }

    private synchronized SessionFactory getSessionFactory(PersistenceUnitConfig config) {
        if (storageSessionFactories.get(config.databaseName()) != null) {
            return storageSessionFactories.get(config.databaseName());
        } else {
            log.info("При попытке создания DAO -> не обнаружено созданной SessionFactory, начинаем создание.");
            SessionFactory sessionFactory = SessionFactoryProvider.getInstance(config).get();

            log.info("Помещаем SessionFactory - в кэш.");
            storageSessionFactories.put(config.databaseName(), sessionFactory);
            return sessionFactory;
        }
    }

    protected void executeTransaction(Consumer<Session> action) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                action.accept(session);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        } catch (Exception e) {
            log.error("Ошибка при создании сессии.");
            throw e;
        }
    }

    protected  <R> R executeTransactionWithResult(Function<Session, R> action) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                R result = action.apply(session);
                transaction.commit();
                return result;
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        } catch (Exception e) {
            log.error("Ошибка при создании сессии.");
            throw e;
        }
    }
}
