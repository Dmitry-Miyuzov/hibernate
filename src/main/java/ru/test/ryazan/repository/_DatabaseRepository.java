package ru.test.ryazan.repository;


public class _DatabaseRepository {
    private static TasksRepository TASKS_REPOSITORY;

    public static synchronized TasksRepository TASKS_REPOSITORY() {
        if (TASKS_REPOSITORY == null) {
            TASKS_REPOSITORY = new TasksRepository();
        }

        return TASKS_REPOSITORY;
    }
}
