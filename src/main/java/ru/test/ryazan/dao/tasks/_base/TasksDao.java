package ru.test.ryazan.dao.tasks._base;

import ru.test.ryazan.dao._base._BaseDao;
import ru.test.ryazan.env.Env;

public class TasksDao extends _BaseDao {
    public TasksDao() {
        super(Env.DB.TASKS);
    }
}
