package ru.test.ryazan.dao.tasks.schema.tasks;

import ru.test.ryazan.dao.tasks._base.TasksDao;

public class DictionaryDao extends TasksDao {
    private static DictionaryDao DAO;

    private DictionaryDao() {
        super();
    }

    public static synchronized DictionaryDao getInstance() {
        if (DAO == null) {
            DAO = new DictionaryDao();
        }
        return DAO;
    }


}
