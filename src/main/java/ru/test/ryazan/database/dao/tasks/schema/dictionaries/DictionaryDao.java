package ru.test.ryazan.database.dao.tasks.schema.dictionaries;

import ru.test.ryazan.database.dao.tasks._base.TasksDao;
import ru.test.ryazan.database.dao.tasks.schema.dictionaries.entity.DictionaryEntity;

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

    public DictionaryEntity getById(Integer id) {
        return executeTransactionWithResult(
                session -> session.get(DictionaryEntity.class, id)
        );
    }
}
