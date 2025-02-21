package ru.test.ryazan.repository;

import ru.test.ryazan.dao.tasks.schema.dictionaries.UserDao;
import ru.test.ryazan.dao.tasks.schema.tasks.DictionaryDao;

public class TasksRepository {
    public SchemaTasks SCHEMA_TASKS;
    public SchemaDictionaries SCHEMA_DICTIONARIES;

    TasksRepository() {
        this.SCHEMA_TASKS = new SchemaTasks();
        this.SCHEMA_DICTIONARIES = new SchemaDictionaries();
    }

    public static class SchemaTasks {
        public final UserDao userDao = UserDao.getInstance();
    }

    public static class SchemaDictionaries {
        public final DictionaryDao dictionaryDao = DictionaryDao.getInstance();
    }
}
