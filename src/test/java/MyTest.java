import org.junit.jupiter.api.Test;
import ru.test.ryazan.database.dao.tasks.schema.dictionaries.entity.DictionaryEntity;
import ru.test.ryazan.database.dao.tasks.schema.tasks.entity.UserEntity;
import ru.test.ryazan.database._repository._DatabaseRepository;

public class MyTest {


    @Test
    public void testik() {
        System.out.println();
        DictionaryEntity dictionaryEntity = _DatabaseRepository.TASKS_REPOSITORY.SCHEMA_DICTIONARIES
                .dictionaryDao
                .getById(1);
        UserEntity byId = _DatabaseRepository.TASKS_REPOSITORY.SCHEMA_TASKS
                .userDao
                .getById(1);
        System.out.println();
    }
}
