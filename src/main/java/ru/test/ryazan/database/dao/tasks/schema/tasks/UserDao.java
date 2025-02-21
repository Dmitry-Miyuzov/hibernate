package ru.test.ryazan.database.dao.tasks.schema.tasks;

import ru.test.ryazan.database.dao.tasks._base.TasksDao;
import ru.test.ryazan.database.dao.tasks.schema.tasks.entity.UserEntity;

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

    public UserEntity getById(Integer id) {
        return executeTransactionWithResult(
                session -> session.get(UserEntity.class, id)
        );
    }
}
