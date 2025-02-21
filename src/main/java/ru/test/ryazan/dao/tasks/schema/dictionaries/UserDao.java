package ru.test.ryazan.dao.tasks.schema.dictionaries;

import ru.test.ryazan.dao.tasks._base.TasksDao;

public class UserDao extends TasksDao {
    private static UserDao DAO;

    private UserDao() {
        super();
    }

    public static synchronized UserDao getInstance() {
        if (DAO == null) {
            DAO = new UserDao();
        }
        return DAO;
    }
}
