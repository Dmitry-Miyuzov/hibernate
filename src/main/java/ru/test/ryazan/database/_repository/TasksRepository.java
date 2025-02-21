package ru.test.ryazan.database._repository;

import ru.test.ryazan.database.dao.tasks.schema.tasks.UserDao;
import ru.test.ryazan.database.dao.tasks.schema.dictionaries.DictionaryDao;

/*
На этом уровне, мы уже храним схемы - в виде классов.
А в схемах - уже даошки, относящиеся в конкретной схеме.
 */
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
