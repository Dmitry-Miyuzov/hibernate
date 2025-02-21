package ru.test.ryazan.database.dao.tasks._base;

import ru.test.ryazan.database.dao._base._BaseDao;
import ru.test.ryazan.env.Env;

public class TasksDao extends _BaseDao {
    public TasksDao() {
        super(Env.DB.TASKS);
    }
}
