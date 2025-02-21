package ru.test.ryazan.env;

import org.aeonbits.owner.ConfigFactory;
import ru.test.ryazan.config._base.PersistenceUnitConfig;
import ru.test.ryazan.config.impl.TasksPersistenceUnitConfig;

public class Env {
    public static class DB {
        public final static PersistenceUnitConfig TASKS = ConfigFactory.create(TasksPersistenceUnitConfig.class);
    }
}
